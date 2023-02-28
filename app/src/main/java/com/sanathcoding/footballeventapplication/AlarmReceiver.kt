package com.sanathcoding.footballeventapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.sanathcoding.footballeventapplication.core.common.FootballValue.EXTRA_MESSAGE

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val  message = intent?.getStringExtra(EXTRA_MESSAGE) ?: return
        Log.d("AlarmReceiver", "Alarm Triggered: $message")
    }
}