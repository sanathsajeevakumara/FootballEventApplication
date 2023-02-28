package com.sanathcoding.footballeventapplication.domain.repository

import com.sanathcoding.footballeventapplication.domain.model.AlarmItem

interface AlarmScheduler {
    fun scheduler(item: AlarmItem)
    fun cancel(item: AlarmItem)
}