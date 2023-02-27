package com.sanathcoding.footballeventapplication.data.remote.repository

import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.data.mapper.matches.toMatch
import com.sanathcoding.footballeventapplication.data.mapper.toTeam
import com.sanathcoding.footballeventapplication.data.remote.FootballApi
import com.sanathcoding.footballeventapplication.domain.model.match.Match
import com.sanathcoding.footballeventapplication.domain.model.teams.Team
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import javax.inject.Inject

class FootballRepositoryImpl @Inject constructor(
    private val api: FootballApi
): FootballRepository {
    override suspend fun getTeamData(): Resource<List<Team>> {
        return try {
            Resource.Success(
                data = api.getTeamData().map {teamsDto ->
                    teamsDto.toTeam()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                e.message ?: "An Unknown error occurred"
            )
        }
    }

    override suspend fun getMatchData(): Resource<Match> {
        return try {
            Resource.Success(
                data = api.getMatchData().toMatch()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                e.message ?: "An Unknown error occurred"
            )
        }
    }

    override suspend fun getMatchByTeamId(id: String): Resource<Match> {
        return try {
            Resource.Success(
                data = api.getMatchByTeamId(id = id).toMatch()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                e.message ?: "An Unknown error occurred"
            )
        }
    }


}