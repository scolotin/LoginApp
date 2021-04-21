package com.scolotin.logintestapp

import android.text.format.DateFormat
import java.util.*

fun getDate(timestamp: Long): String {
    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = timestamp * 1000L
    return DateFormat.format("dd-MM-yyyy", calendar).toString()
}