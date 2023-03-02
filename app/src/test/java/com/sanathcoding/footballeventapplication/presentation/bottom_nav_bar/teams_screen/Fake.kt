package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import com.sanathcoding.footballeventapplication.domain.model.match.Match
import com.sanathcoding.footballeventapplication.domain.model.match.Matches
import com.sanathcoding.footballeventapplication.domain.model.match.Previous
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming
import com.sanathcoding.footballeventapplication.domain.model.teams.Team
import com.sanathcoding.footballeventapplication.domain.model.teams.Teams
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
object Fake {

    fun buildTeams(count: Int): Pair<String, Teams> {
        val teams = arrayListOf<Team>()

        repeat(count) { num ->
            val team = Team(
                id = num.toString(),
                logo = "",
                name = "name $num"
            )
            teams.add(team)
        }

        val teamRes = Teams(teams)
        return Pair(Json.encodeToString(teamRes), teamRes)
    }

    fun buildMatches(count: Int): Pair<String, Match> {
        val previousList = arrayListOf<Previous>()
        val upComingList = arrayListOf<Upcoming>()

        repeat(count) {
            val previous = Previous(
                away = "away $it",
                date = "",
                description = "",
                highlights = "",
                home = "home $it",
                winner = ""
            )


            val upcoming = Upcoming(
                away = "away $it",
                date = "",
                description = "",
                home = "home $it",
            )

            previousList.add(previous)
            upComingList.add(upcoming)
        }

        val matchesRes = Matches(previous = previousList, upcoming = upComingList)
        val match = Match(matchesRes)

        return Pair(Json.encodeToString(match), match)
    }

}