package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.team_detail_screen

import com.sanathcoding.footballeventapplication.domain.model.match.Match

data class TeamDetailState(
    val match: Match? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)