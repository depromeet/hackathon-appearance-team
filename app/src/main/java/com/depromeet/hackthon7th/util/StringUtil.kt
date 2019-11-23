package com.depromeet.hackthon7th.util

import com.depromeet.hackthon7th.R
import com.depromeet.hackthon7th.RootApplication
import java.util.*


const val timeFormat = "%d월 %d일 (%s) %s %d:%d"
val dayOfWeek = arrayOf("일", "월", "화", "수", "목", "금", "토")

fun getDateTime(date: Date) {

}

fun getCurrentDateTime(): String {
    val cal = Calendar.getInstance()

    return String.format(
        RootApplication.getContext().getString(R.string.format_datetime),
        cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DATE),
        dayOfWeek[cal.get(Calendar.DAY_OF_WEEK) - 1],
        if (cal.get(Calendar.AM_PM) == 0) "오전" else "오후",
        cal.get(Calendar.HOUR),
        cal.get(Calendar.MINUTE)
    )
}