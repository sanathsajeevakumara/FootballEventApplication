package com.sanathcoding.footballeventapplication.domain.model.teams

@kotlinx.serialization.Serializable
data class Team(
    val id: String,
    val logo: String,
    val name: String,
)
