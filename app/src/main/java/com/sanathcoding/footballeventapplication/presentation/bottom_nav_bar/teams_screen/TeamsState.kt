package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import com.sanathcoding.footballeventapplication.domain.model.teams.Team
import com.sanathcoding.footballeventapplication.domain.model.teams.Teams

data class TeamsState(
    val team: List<Team> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
