package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val footballRepository: FootballRepository,
    private val application: Application
): ViewModel() {

    var state by mutableStateOf(TeamsState())
        private set

    init { loadTeamsData() }

    fun loadTeamsData() {
        viewModelScope.launch {

            state = state.copy(isLoading = true)

            footballRepository.getTeamData().onEach { resource ->
                when (resource) {
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
//                            error = resource.message ?: application.getString(R.string.http_exception),
                            error = resource.message ?:
                            "An Unexpected error occurred.",
                            isLoading = false,
                            team = emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}