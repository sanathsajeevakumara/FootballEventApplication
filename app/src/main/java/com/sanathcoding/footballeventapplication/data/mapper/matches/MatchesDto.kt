package com.sanathcoding.footballeventapplication.data.mapper.matches

import com.sanathcoding.footballeventapplication.data.remote.dto.match_dto.MatchesDto
import com.sanathcoding.footballeventapplication.domain.model.match.Matches

fun MatchesDto.toMatches(): Matches {
    return Matches(
        previous = previous.map { previousDto ->
            previousDto.toUpcoming()
        },
        upcoming = upcoming.map { upcomingDto ->
            upcomingDto.toUpcoming()
        }
    )
}

fun Matches.toMatchesDto(): MatchesDto {
    return MatchesDto(
        previous = previous.map { previousDto ->
            previousDto.toUpcomingDto()
        },
        upcoming = upcoming.map { upcomingDto ->
            upcomingDto.toUpcomingDto()
        }
    )
}