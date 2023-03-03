package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sanathcoding.footballeventapplication.core.common.TestTag.TEAM_LIST
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.mockserver.SuccessDispatcher
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.base.BaseScreenTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalMaterialApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class EndToEndTest: BaseScreenTest() {

    @Test
    @InternalCoroutinesApi
    fun checkTeamsAreLoadedCorrectly() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Set the mock server's dispatcher
        mockServer.dispatcher = SuccessDispatcher()

        // Set the main content
        setMainContent()

        // Verify that the list of teams is displayed
        composeRule.apply {

            onNodeWithContentDescription(TEAM_LIST).onChildren().assertCountEquals(10)

            // Click on a team item that text contain "Team Red Dragons"
            onNodeWithText("Team Red Dragons").performClick()

            // verify that the team's screen previous match text is displayed
            onNodeWithText(context.getString(R.string.previous_match)).assertIsDisplayed()
        }
    }

}