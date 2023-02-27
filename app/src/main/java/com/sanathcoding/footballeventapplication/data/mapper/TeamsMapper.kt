package com.sanathcoding.footballeventapplication.data.mapper

import com.sanathcoding.footballeventapplication.data.remote.dto.teams_dto.TeamsDto
import com.sanathcoding.footballeventapplication.domain.model.teams.Team

fun TeamsDto.toTeam(): Team {
    return Team(
        id = id,
        logo = logo,
        name = name
    )
}

fun Team.toTeamsDto(): TeamsDto {
    return TeamsDto(
        id = id,
        logo = logo,
        name = name
    )
}