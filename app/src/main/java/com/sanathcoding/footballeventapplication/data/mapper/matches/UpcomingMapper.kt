package com.sanathcoding.footballeventapplication.data.mapper.matches

import com.sanathcoding.footballeventapplication.data.remote.dto.match_dto.UpcomingDto
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming

fun UpcomingDto.toUpcoming(): Upcoming {
    return Upcoming(
        away = away,
        date = date,
        description = description,
        home = home
    )
}

fun Upcoming.toUpcomingDto(): UpcomingDto {
    return UpcomingDto(
        away = away,
        date = date,
        description = description,
        home = home
    )
}