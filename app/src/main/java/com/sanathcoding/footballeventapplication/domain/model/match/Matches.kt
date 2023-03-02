package com.sanathcoding.footballeventapplication.domain.model.match

@kotlinx.serialization.Serializable
data class Matches(
    val previous: List<Previous>,
    val upcoming: List<Upcoming>
)