package com.sanathcoding.footballeventapplication.core.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun dateTimeConverter(date: String): String {
    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("dd MM yyyy HH:mm a", Locale.US)
    val formattedDate = formatter.format(parser.parse(date))

    Log.d("Date", "Date: $formattedDate")
    return formattedDate
}

fun dateConverter(date: String): String {
    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("dd MM", Locale.US)
    val formattedDate = formatter.format(parser.parse(date))
    return formattedDate
}

fun timeConverter(date: String): String {
    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("HH:mm", Locale.US)
    val formattedTime = formatter.format(parser.parse(date))
    return formattedTime
}