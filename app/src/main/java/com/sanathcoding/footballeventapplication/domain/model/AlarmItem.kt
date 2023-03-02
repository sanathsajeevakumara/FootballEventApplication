package com.sanathcoding.footballeventapplication.domain.model

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime = LocalDateTime.now(),
    val message: String = ""
)
