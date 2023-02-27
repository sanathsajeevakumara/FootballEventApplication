package com.sanathcoding.footballeventapplication.data.remote

import com.sanathcoding.footballeventapplication.data.remote.dto.match_dto.MatchDto
import com.sanathcoding.footballeventapplication.data.remote.dto.teams_dto.TeamsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApi {

    @GET("/teams")
    suspend fun getTeamData(): List<TeamsDto>

    @GET("/teams/matches")
    suspend fun getMatchData(): MatchDto

    @GET("/teams/{id}/matches")
    suspend fun getMatchByTeamId(@Path("id") id: String): MatchDto

}