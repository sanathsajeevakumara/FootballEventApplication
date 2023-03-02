package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.data.remote.FootballApi
import com.sanathcoding.footballeventapplication.data.remote.repository.FakeFootballRepository
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.MainDispatcherRule
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.Fake
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Retrofit
@OptIn(ExperimentalCoroutinesApi::class)
class MatchScreenViewModelTest {

    private lateinit var repository: FakeFootballRepository
    private lateinit var mockWebServer: MockWebServer
    private lateinit var footBallApi: FootballApi
    private lateinit var viewModel: MatchScreenViewModel
    private lateinit var application: Application

    private val client = OkHttpClient.Builder().build()
    private val contentType = "application/json".toMediaType()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


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
        viewModel = MatchScreenViewModel(repository, application)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When fetching Match data, Match state is shown`() = runTest {
        var state by mutableStateOf(MatchState())
        repository.getMatchData().onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    state = state.copy(
                        match = resource.data,
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
                        match = null
                    )
                }
            }
        }
        viewModel.loadMatchData()
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(state.match, viewModel.state.match)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check successful response`() = runTest {
        val fakeRes = Fake.buildMatches(10)

        val response = MockResponse()
            .setBody(fakeRes.first)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = repository.getMatchData()
        launch {
            flow.onEach {
                assertTrue(it is Resource.Success)
                assertTrue((it as Resource.Success).data == fakeRes.second)
            }
        }
    }

}