package com.sanathcoding.footballeventapplication.domain.repository

import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.domain.model.match.Match
import com.sanathcoding.footballeventapplication.domain.model.teams.Team
import kotlinx.coroutines.flow.Flow

interface FootballRepository {

    suspend fun getTeamData(): Flow<Resource<List<Team>>>

    suspend fun getMatchData(): Flow<Resource<Match>>

    suspend fun getMatchByTeamId(id: String): Flow<Resource<Match>>

}