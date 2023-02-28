package com.sanathcoding.footballeventapplication.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.core.common.FootballValue.CHANNEL_ID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FootballApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}