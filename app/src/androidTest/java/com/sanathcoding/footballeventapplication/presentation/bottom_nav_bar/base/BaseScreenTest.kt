package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.base

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sanathcoding.footballeventapplication.presentation.MainActivity
import com.sanathcoding.footballeventapplication.presentation.navigation.SetUpNavGraph
import com.sanathcoding.footballeventapplication.ui.theme.FootballEventApplicationTheme
import dagger.hilt.android.testing.HiltAndroidRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseScreenTest {

    @get:Rule(order = 0)
    val hiltRule by lazy { HiltAndroidRule(this) }

    @get:Rule(order = 1)
    val composeRule by lazy { createAndroidComposeRule<MainActivity>() }

    val mockServer by lazy { MockWebServer() }

    @Before
    fun setUp() {
        hiltRule.inject()
        mockServer.start(8080)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    fun setMainContent() {
        composeRule.activity.setContent {
            FootballEventApplicationTheme {
                SetUpNavGraph()
            }
        }
    }

}