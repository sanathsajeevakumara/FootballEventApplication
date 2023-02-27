package com.sanathcoding.footballeventapplication.domain.repository

import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.domain.model.match.Match
import com.sanathcoding.footballeventapplication.domain.model.teams.Team

interface FootballRepository {

    suspend fun getTeamData(): Resource<List<Team>>

    suspend fun getMatchData(): Resource<Match>

    suspend fun getMatchByTeamId(id: String): Resource<Match>

}