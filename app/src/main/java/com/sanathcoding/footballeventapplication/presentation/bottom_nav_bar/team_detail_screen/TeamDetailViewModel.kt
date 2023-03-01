package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.team_detail_screen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.core.common.FootballValue.TEAM_ID
import com.sanathcoding.footballeventapplication.core.common.FootballValue.TEAM_NAME
import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val repository: FootballRepository,
    private val application: Application,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(TeamDetailState())
        private set

    var teamName by mutableStateOf("")

    init {
        savedStateHandle.get<String>(TEAM_ID)?.let { teamId ->
            loadMatchDataById(teamId)
        }
        savedStateHandle.get<String>(TEAM_NAME)?.let { name ->
            teamName = name
        }
    }

    private fun loadMatchDataById(id: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            repository.getMatchByTeamId(id).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        state = state.copy(
                            match = resource.data,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = resource.message ?: application.getString(R.string.http_exception),
                            isLoading = false,
                            match = null
                        )
                    }
                }
            }.launchIn(viewModelScope)

        }
    }
}