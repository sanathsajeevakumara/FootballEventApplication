package com.sanathcoding.footballeventapplication.data.remote.repository

import android.app.Application
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.core.common.Resource
import com.sanathcoding.footballeventapplication.data.mapper.matches.toMatch
import com.sanathcoding.footballeventapplication.data.mapper.teams.toTeam
import com.sanathcoding.footballeventapplication.data.remote.FootballApi
import com.sanathcoding.footballeventapplication.domain.model.match.Match
import com.sanathcoding.footballeventapplication.domain.model.teams.Team
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FootballRepositoryImpl @Inject constructor(
    private val api: FootballApi,
    private val application: Application
) : FootballRepository {
    override suspend fun getTeamData(): Flow<Resource<List<Team>>> = flow {
        try {
            emit(Resource.Loading())
            val teamData = api.getTeamData().teams.map {
                it.toTeam()
            }
            emit(Resource.Success(data = teamData))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?:
                    application.getString(R.string.http_exception)
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(application.getString(R.string.connection_exception))
            )
        }
    }

    override suspend fun getMatchData(): Flow<Resource<Match>> = flow {
        try {
            emit(Resource.Success(data = api.getMatchData().toMatch()))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?:
                    application.getString(R.string.http_exception)
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(application.getString(R.string.connection_exception))
            )
        }
    }

    override suspend fun getMatchByTeamId(id: String): Flow<Resource<Match>> = flow {
        try {
            emit(
                Resource.Success(data = api.getMatchByTeamId(id = id).toMatch())
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?:
                    application.getString(R.string.http_exception)
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(application.getString(R.string.connection_exception))
            )
        }
    }


}