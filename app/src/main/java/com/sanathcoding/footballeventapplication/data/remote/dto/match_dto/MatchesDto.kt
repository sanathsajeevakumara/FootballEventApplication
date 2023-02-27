package com.sanathcoding.footballeventapplication.data.remote.dto.match_dto

data class MatchesDto(
    val previous: List<PreviousDto>,
    val upcoming: List<UpcomingDto>
)