package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.MainDispatcherRule
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.TeamsState
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.TeamsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MatchScreenViewModelTest {
    private val repository = mockk<FootballRepository>(relaxed = true)
    private lateinit var viewModel: MatchScreenViewModel
    private lateinit var application: Application

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        application = mockk()
        viewModel = MatchScreenViewModel(repository, application)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When fetching Match data, Match state is shown`() = runTest {
        var state by mutableStateOf(MatchState())
        coEvery {
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
        } returns mockk()
        viewModel.loadMatchData()
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(state.match, viewModel.state.match)
    }
}