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

fun getCurrentTime(): String {
    val cal = Calendar.getInstance()

    return String.format(
        "%s %d:%02d",
        if (cal.get(Calendar.AM_PM) == 0) "오전" else "오후",
        cal.get(Calendar.HOUR),
        cal.get(Calendar.MINUTE)
    )
}

fun getBeforeTime(value: Float): String {
    val hour = -(value / 60).toInt()
    val minutes = -(value % 60).toInt()

    return "${hour}시간 ${minutes}분 전"
}

fun getAfterTime(value: Float): String {
    val hour = value.toInt() / 60
    val minutes = value.toInt() % 60

    return "${hour}시간 ${minutes}분 전"
}