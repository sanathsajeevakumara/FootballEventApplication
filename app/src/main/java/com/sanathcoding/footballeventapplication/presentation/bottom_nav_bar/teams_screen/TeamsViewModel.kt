package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val footballRepository: FootballRepository
): ViewModel() {

    var state by mutableStateOf(TeamsState())
        private set

    init {
        loadTeamsData()
    }

    fun loadTeamsData() {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(
                isLoading = true,
                error = null
            )
            footballRepository.getTeamData().onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        state = state.copy(
                            team = resource.data ?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true,
                            team = emptyList()
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = resource.message,
                            isLoading = false,
                            team = emptyList()
                        )
                    }
                }
            }
        }
    }

}