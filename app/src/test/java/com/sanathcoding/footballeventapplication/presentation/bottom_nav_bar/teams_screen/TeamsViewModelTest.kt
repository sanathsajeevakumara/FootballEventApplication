@file:OptIn(ExperimentalCoroutinesApi::class)

package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.data.remote.FootballApi
import com.sanathcoding.footballeventapplication.data.remote.repository.FakeFootballRepository
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.MainDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import kotlinx.serialization.json.Json
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Retrofit

class TeamsViewModelTest {

    private lateinit var repository: FakeFootballRepository
    private lateinit var mockWebServer: MockWebServer
    private lateinit var footBallApi: FootballApi
    private lateinit var viewModel: TeamsViewModel
    private lateinit var application: Application

    private val client = OkHttpClient.Builder().build()
    private val contentType = "application/json".toMediaType()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = StandardTestDispatcher()


    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setUp() {

        val jsonConverter = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }

        mockWebServer = MockWebServer()

        footBallApi = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .client(client).addConverterFactory(jsonConverter.asConverterFactory(contentType))
            .build().create(FootballApi::class.java)

        application = mockk()
        repository = FakeFootballRepository()
        viewModel = TeamsViewModel(repository, application)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When fetching `() = runTest {
        var state by mutableStateOf(TeamsState())
        repository.getTeamData().onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    state = state.copy(
                        team = resource.data ?: emptyList(),
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true,
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        error = resource.message ?:
                        "An Unexpected error occurred.",
                        isLoading = false,
                        team = emptyList()
                    )
                }
            }
        }
        viewModel.loadTeamsData()
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(state.isLoading, viewModel.state.isLoading)
    }

    @Test
    fun `check if exception is thrown for malformed JSON`() = runTest {
        val response = MockResponse().setBody("Malformed JSON").setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = repository.getTeamData()
        launch {
            flow.collect {
                assertTrue(it is Resource.Success)
                (it as Resource.Success).message?.contains("Malformed JSON")
            }
        }

    }

    @Test
    fun `check successful response`() = runTest {
        val fakeRes = Fake.buildTeams(10)

        val response = MockResponse()
            .setBody(fakeRes.first)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = repository.getTeamData()
        launch {
            flow.onEach {
                assertTrue(it is Resource.Success)
                assertTrue((it as Resource.Success).data == fakeRes.second.teams)
            }
        }
    }

}