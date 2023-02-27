package com.sanathcoding.footballeventapplication.presentation.navigation

import com.sanathcoding.footballeventapplication.core.common.FootballValue.HOME_SCREEN
import com.sanathcoding.footballeventapplication.core.common.FootballValue.MATCH_SCREEN
import com.sanathcoding.footballeventapplication.core.common.FootballValue.TEAMS_SCREEN
import com.sanathcoding.footballeventapplication.core.common.FootballValue.TEAM_DETAIL_SCREEN

sealed class Screen(val route: String) {

    object HomeScreen: Screen(HOME_SCREEN)
    object TeamsScreen: Screen(TEAMS_SCREEN)
    object MatchScreen: Screen(MATCH_SCREEN)
    object TeamDetailScreen: Screen(TEAM_DETAIL_SCREEN)
}
