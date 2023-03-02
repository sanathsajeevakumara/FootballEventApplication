package com.sanathcoding.footballeventapplication.data.remote.repository

import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.domain.model.match.Match
import com.sanathcoding.footballeventapplication.domain.model.match.Matches
import com.sanathcoding.footballeventapplication.domain.model.teams.Team
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFootballRepository: FootballRepository {

    private val teams = mutableListOf<Team>()
    private val match = Match(matches = Matches(previous = emptyList(), upcoming = emptyList()))

    override suspend fun getTeamData(): Flow<Resource<List<Team>>> {
        return flow {
            emit(Resource.Success(teams))
        }
    }

    override suspend fun getMatchData(): Flow<Resource<Match>> {
        return flow {
            emit(Resource.Success(match))
        }
    }

    override suspend fun getMatchByTeamId(id: String): Flow<Resource<Match>> {
        return flow {
            emit(Resource.Success(match))
        }
    }

}