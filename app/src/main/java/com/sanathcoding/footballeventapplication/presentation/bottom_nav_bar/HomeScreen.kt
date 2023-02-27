package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sanathcoding.footballeventapplication.presentation.BottomNavBar
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.MatchScreen
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.TeamsScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0)}

    Scaffold(
        bottomBar = { BottomNavBar {selectedItem = it} }
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            when (selectedItem){
                0 -> TeamsScreen(navController)
                1 -> MatchScreen()
            }
        }
    }
}