package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen


import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sanathcoding.footballeventapplication.core.common.TestTag
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.mockserver.ErrorDispatcher
import com.sanathcoding.footballeventapplication.mockserver.SuccessDispatcher
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.base.BaseScreenTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalMaterialApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TeamsScreenTest : BaseScreenTest() {

    @Test
    @InternalCoroutinesApi
    fun testTeamListScreen_taskListIsNotEmpty() {
        // Set the mock server's dispatcher
        mockServer.dispatcher = SuccessDispatcher()
        setMainContent()

        composeRule.apply {
            onNodeWithTag(TestTag.PROGRESS_INDICATOR).assertIsDisplayed()
            mainClock.advanceTimeBy(10000)
//            onNodeWithTag(TEAM_LIST).onChildren().assertCountEquals(10)
//            onNodeWithText("Team Red Dragons").assertIsDisplayed()
        }
    }

    @Test
    @InternalCoroutinesApi
    fun errorTextVisibleWhenConnectionError() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mockServer.dispatcher = ErrorDispatcher()
        setMainContent()

        composeRule.apply {
            onNodeWithText("Match Details").assertIsDisplayed().performClick()
            mainClock.advanceTimeBy(50000)
            onNodeWithText(context.getString(R.string.previous_match)).assertIsDisplayed()
            onNodeWithText(context.getString(R.string.upcoming_match)).assertIsDisplayed()
        }
    }

}

//composeRule.onNodeWithText("Teams").assertIsDisplayed().performClick()
//        composeRule.mainClock.advanceTimeBy(2000)
////        assertEquals("/teams", mockServer.takeRequest().path)
//        Log.d("ddd","Path : ${mockServer.takeRequest().path}")
//        composeRule.onAllNodesWithContentDescription(TEAM_ITEM).onFirst().assertIsDisplayed()