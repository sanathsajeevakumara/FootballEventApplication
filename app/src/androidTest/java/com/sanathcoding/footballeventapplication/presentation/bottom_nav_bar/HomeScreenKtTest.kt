package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sanathcoding.footballeventapplication.mockserver.SuccessDispatcher
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.base.BaseScreenTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalMaterialApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeScreenKtTest: BaseScreenTest() {
    @InternalCoroutinesApi
    @Test
    fun bottom_tabs_displayed() {
        mockServer.dispatcher = SuccessDispatcher()
        setMainContent()

        composeRule.apply {
            onNodeWithText("Teams", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithText("Match Details", useUnmergedTree = true).assertIsDisplayed()
        }
    }
}