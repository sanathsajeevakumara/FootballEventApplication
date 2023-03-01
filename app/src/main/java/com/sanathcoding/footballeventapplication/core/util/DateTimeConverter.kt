package com.sanathcoding.footballeventapplication.core.util

import java.text.SimpleDateFormat
import java.util.*

fun dateTimeConverter(date: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("dd MM yyyy HH:mm a", Locale.US)
    return formatter.format(parser.parse(date))
}

fun dateConverter(date: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("dd-MM-yy", Locale.US)
    return formatter.format(parser.parse(date))
}

fun timeConverter(date: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("HH:mm a", Locale.US)
    return formatter.format(parser.parse(date))
}