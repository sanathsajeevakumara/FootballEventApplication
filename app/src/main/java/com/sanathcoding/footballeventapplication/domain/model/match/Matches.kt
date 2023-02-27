package com.sanathcoding.footballeventapplication.domain.model.match


data class Matches(
    val previous: List<Previous>,
    val upcoming: List<Upcoming>
)