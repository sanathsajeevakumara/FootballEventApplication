package com.sanathcoding.footballeventapplication.core.util

import java.text.SimpleDateFormat
import java.util.*

fun dateTimeConverter(date: String): String? {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("dd MM yyyy HH:mm a", Locale.US)
    val newDate = parser.parse(date)
//    return formatter.format(newDate)
    return parser.parse(date)?.let { formatter.format(it) }
}

fun dateConverter(date: String): String? {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("dd MM", Locale.US)
//    return formatter.format(parser.parse(date))
    return parser.parse(date)?.let { formatter.format(it) }
}

fun timeConverter(date: String): String? {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val formatter = SimpleDateFormat("HH:mm", Locale.US)
    return parser.parse(date)?.let { formatter.format(it) }
}