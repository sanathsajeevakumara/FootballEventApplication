package com.sanathcoding.footballeventapplication.domain.model.match

@kotlinx.serialization.Serializable
data class Upcoming(
    val away: String,
    val date: String,
    val description: String,
    val home: String
)
