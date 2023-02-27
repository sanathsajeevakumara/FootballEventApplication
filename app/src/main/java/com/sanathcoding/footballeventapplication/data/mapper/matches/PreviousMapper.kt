package com.sanathcoding.footballeventapplication.data.mapper.matches

import com.sanathcoding.footballeventapplication.data.remote.dto.match_dto.PreviousDto
import com.sanathcoding.footballeventapplication.data.remote.dto.match_dto.UpcomingDto
import com.sanathcoding.footballeventapplication.domain.model.match.Previous
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming

fun PreviousDto.toUpcoming(): Previous {
    return Previous(
        away = away,
        date = date,
        description = description,
        highlights = highlights,
        home = home,
        winner = winner
    )
}

fun Previous.toUpcomingDto(): PreviousDto {
    return PreviousDto(
        away = away,
        date = date,
        description = description,
        highlights = highlights,
        home = home,
        winner = winner
    )
}