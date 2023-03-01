package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sanathcoding.footballeventapplication.core.common.TestTag.PROGRESS_INDICATOR
import com.sanathcoding.footballeventapplication.core.common.TestTag.TEAM_LIST
import com.sanathcoding.footballeventapplication.di.FootballModule
import com.sanathcoding.footballeventapplication.di.FootballRepositoryModule
import com.sanathcoding.footballeventapplication.presentation.MainActivity
import com.sanathcoding.footballeventapplication.presentation.navigation.Screen
import com.sanathcoding.footballeventapplication.ui.theme.FootballEventApplicationTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(FootballModule::class, FootballRepositoryModule::class)
class TeamsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            FootballEventApplicationTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.TeamsScreen.route
                ) {
                    composable(Screen.TeamsScreen.route) {
                        TeamsScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun testTeamListScreen_taskListIsNotEmpty() {
        composeRule.onNodeWithTag(PROGRESS_INDICATOR).assertDoesNotExist()
        composeRule.onNodeWithTag(PROGRESS_INDICATOR).assertIsDisplayed()
    }

//    @Test
//    fun testTeamListScreen_taskListIsEmpty() {
//        composeRule.onNodeWithTag(TEAM_LIST).onChildren().assertCountEquals(0)
//    }

}