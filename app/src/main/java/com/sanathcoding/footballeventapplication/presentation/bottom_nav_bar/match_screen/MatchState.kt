package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen

import com.sanathcoding.footballeventapplication.domain.model.match.Match

data class MatchState(
    val match: Match? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)