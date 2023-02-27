package com.sanathcoding.footballeventapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.MatchScreen
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.TeamsScreen
import com.sanathcoding.footballeventapplication.ui.theme.FootballEventApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballEventApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    TeamsScreen(navController = navController)
//                    MatchScreen()
                }
            }
        }
    }
}