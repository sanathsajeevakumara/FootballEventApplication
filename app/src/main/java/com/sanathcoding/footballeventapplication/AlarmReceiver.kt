package com.sanathcoding.footballeventapplication

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import com.sanathcoding.footballeventapplication.core.common.FootballValue
import com.sanathcoding.footballeventapplication.core.common.FootballValue.EXTRA_MESSAGE

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val  message = intent?.getStringExtra(EXTRA_MESSAGE) ?: return
        Log.d("AlarmReceiver", "Alarm Triggered: $message")
        showNotification(context, message)
    }

    private fun showNotification(context: Context?, message: String) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, FootballValue.CHANNEL_ID)
            .setContentTitle("Match Reminder")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(1, notification)
    }
}