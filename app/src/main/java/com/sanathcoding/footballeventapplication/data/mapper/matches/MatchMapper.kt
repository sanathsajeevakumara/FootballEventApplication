package com.sanathcoding.footballeventapplication.data.mapper.matches

import com.sanathcoding.footballeventapplication.data.remote.dto.match_dto.MatchDto
import com.sanathcoding.footballeventapplication.domain.model.match.Match

fun MatchDto.toMatch(): Match {
    return Match(
        matches = matches.toMatches()
    )
}

fun Match.toMatchDto(): MatchDto {
    return MatchDto(
        matches = matches.toMatchesDto()
    )
}