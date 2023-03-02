package com.sanathcoding.footballeventapplication.mockserver

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {

    fun successDispatcher(map: Map<String, String>): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {

                    "/teams" -> {
                        var json: String = ""
                        if (map.containsKey("/teams")) {
                            json = map["/teams"]!!
                        }
                        MockResponse().setResponseCode(200)
                            .setBody(getJsonContent(json))
                    }

                    "/teams/matches" -> {
                        var json: String = ""
                        if (map.containsKey("/teams/matches")) {
                            json = map["/teams/matches"]!!
                        }
                        MockResponse().setResponseCode(200)
                            .setBody(getJsonContent(json))
                    }

                    "/teams/{id}/matches" -> {
                        var json: String = ""
                        if (map.containsKey("/teams/{id}/matches")) {
                            json = map["/teams/{id}/matches"]!!
                        }
                        MockResponse().setResponseCode(200)
                            .setBody(getJsonContent(json))
                    }

//                    "/teams" -> MockResponse().setResponseCode(200)
//                        .setBody(getJsonContent("teams.json"))
//                    "/teams/matches" -> MockResponse().setResponseCode(200)
//                        .setBody(getJsonContent("matches.json"))
//                    "/teams/{id}/matches" -> MockResponse().setResponseCode(200)
//                        .setBody(getJsonContent("matches_by_id.json"))
                    else -> MockResponse().setResponseCode(400)
                }
            }
        }
    }

    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
        }
    }

}

fun getJsonContent(fileName: String): String {
    val context = ApplicationProvider.getApplicationContext<Context>()
    return InputStreamReader(
        context.javaClass.classLoader!!.getResourceAsStream(fileName)
    ).toString()
}