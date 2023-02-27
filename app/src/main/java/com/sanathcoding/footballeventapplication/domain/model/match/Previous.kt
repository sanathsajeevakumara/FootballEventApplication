package com.sanathcoding.footballeventapplication.domain.model.match

data class Previous(
    val away: String,
    val date: String,
    val description: String,
    val highlights: String,
    val home: String,
    val winner: String
)
