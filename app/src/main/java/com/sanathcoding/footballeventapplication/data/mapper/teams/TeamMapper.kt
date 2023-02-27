package com.sanathcoding.footballeventapplication.data.mapper.teams

import com.sanathcoding.footballeventapplication.data.remote.dto.teams_dto.TeamDto
import com.sanathcoding.footballeventapplication.domain.model.teams.Team

fun TeamDto.toTeam(): Team {
    return Team(
        id = id,
        logo = logo,
        name = name
    )
}

fun Team.toTeamDto(): TeamDto {
    return TeamDto(
        id = id,
        logo = logo,
        name = name
    )
}