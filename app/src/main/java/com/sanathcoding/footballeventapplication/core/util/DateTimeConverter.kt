package com.sanathcoding.footballeventapplication.core.util

import java.text.SimpleDateFormat
import java.util.*

val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
val dateFormatter = SimpleDateFormat("dd-MM-yy", Locale.US)
val timeFormatter = SimpleDateFormat("HH:mm a", Locale.US)
var parserDate: Date = Date()

fun dateConverter(date: String): String {
    parser.parse(date)?.let { parserDate = it }
    return dateFormatter.format(parserDate)
}

fun timeConverter(date: String): String {
    parser.parse(date)?.let { parserDate = it }
    return timeFormatter.format(parserDate)
}