package com.sanathcoding.footballeventapplication.mockserver

import android.content.Context
import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry
import com.sanathcoding.footballeventapplication.mockserver.AssetReaderUtil.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

const val TEAMS = "/teams"
const val MATCHES = "/teams/matches"
const val MATCHES_BY_TEAM = "/teams/{id}/matches"
const val TEAMS_SUCCESS = "teams.json"
const val MATCHES_SUCCESS = "matches.json"
const val MATCHES_BY_TEAM_SUCCESS = "matches_by_team.json"

class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
): Dispatcher() {

    private val responseFilesByPath: Map<String, String> = mapOf(
        TEAMS to TEAMS_SUCCESS,
        MATCHES to MATCHES_SUCCESS,
        MATCHES_BY_TEAM to MATCHES_BY_TEAM_SUCCESS
    )


    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        val pathWithoutQueryParams = Uri.parse(request.path).path ?: return errorResponse
        val responseFile = responseFilesByPath[pathWithoutQueryParams]

        return if (responseFile != null) {
            val responseBody = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        } else {
            throw Throwable("Uri.parse(request.path).path null")
            errorResponse
        }
    }
}