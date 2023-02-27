package com.sanathcoding.footballeventapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanathcoding.footballeventapplication.core.common.FootballValue.TEAM_ID
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.HomeScreen
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.MatchScreen
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.team_detail_screen.TeamDetailScreen
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.TeamsScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.TeamDetailScreen.route + "/{$TEAM_ID}") {
            TeamDetailScreen()
        }
    }
}