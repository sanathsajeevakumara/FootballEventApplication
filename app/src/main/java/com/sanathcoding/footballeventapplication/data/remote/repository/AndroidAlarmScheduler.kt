package com.sanathcoding.footballeventapplication.data.remote.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.sanathcoding.footballeventapplication.core.common.FootballValue.EXTRA_MESSAGE
import com.sanathcoding.footballeventapplication.AlarmReceiver
import com.sanathcoding.footballeventapplication.domain.model.AlarmItem
import com.sanathcoding.footballeventapplication.domain.repository.AlarmScheduler
import java.time.ZoneId

class AndroidAlarmScheduler(
    private val context: Context
): AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun scheduler(item: AlarmItem) {

        val intent = Intent(
            context,
            AlarmReceiver::class.java
        ).apply {
            putExtra(EXTRA_MESSAGE, item.message)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}