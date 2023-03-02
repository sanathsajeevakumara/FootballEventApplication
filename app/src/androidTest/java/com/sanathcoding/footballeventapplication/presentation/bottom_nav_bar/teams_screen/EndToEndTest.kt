package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.sanathcoding.footballeventapplication.core.common.TestTag.TEAM_LIST
import com.sanathcoding.footballeventapplication.di.FootballModule
import com.sanathcoding.footballeventapplication.di.FootballRepositoryModule
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.mockserver.MockServerDispatcher
import com.sanathcoding.footballeventapplication.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(FootballModule::class, FootballRepositoryModule::class)
class EndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mockServer: MockWebServer

    private val serviceMap: Map<String, String> = mapOf(
        Pair("/teams", "teams.json"),
        Pair("/teams/matches", "matches.json"),
        Pair("/teams/{id}/matches", "matches_by_id.json")
    )

    private var idlingResource: IdlingResource? = null


    @Before
    fun setUp() {
        hiltRule.inject()

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        activityScenario.onActivity{ activity ->
            idlingResource = activity.getIdlingResource()
        }

        mockServer = MockWebServer()
         mockServer.start(8080)

        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun checkTeamsAreLoadedCorrectly() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        mockServer.dispatcher = MockServerDispatcher().successDispatcher(serviceMap)

        val request: RecordedRequest = mockServer.takeRequest()
        assertEquals("/teams", request.path)

        composeRule.onNodeWithContentDescription(TEAM_LIST).onChildren().assertCountEquals(10)
        composeRule.onNodeWithText("Team Red Dragons").performClick()

        composeRule.onNodeWithText(context.getString(R.string.previous_match)).assertIsDisplayed()
    }

}