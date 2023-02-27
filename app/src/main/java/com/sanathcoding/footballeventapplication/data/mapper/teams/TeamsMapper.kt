package com.sanathcoding.footballeventapplication.data.mapper

import com.sanathcoding.footballeventapplication.data.mapper.teams.toTeam
import com.sanathcoding.footballeventapplication.data.mapper.teams.toTeamDto
import com.sanathcoding.footballeventapplication.data.remote.dto.teams_dto.TeamsDto
import com.sanathcoding.footballeventapplication.domain.model.teams.Team
import com.sanathcoding.footballeventapplication.domain.model.teams.Teams

fun TeamsDto.toTeams(): Teams {
    return Teams(
        teams = teams.map {
            it.toTeam()
        }
    )
}

fun Teams.toTeamsDto(): TeamsDto {
    return TeamsDto(
        teams = teams.map {
            it.toTeamDto()
        }
    )
}