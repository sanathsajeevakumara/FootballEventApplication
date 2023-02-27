package com.sanathcoding.footballeventapplication.data.remote.dto.match_dto

data class PreviousDto(
    val away: String,
    val date: String,
    val description: String,
    val highlights: String,
    val home: String,
    val winner: String
)