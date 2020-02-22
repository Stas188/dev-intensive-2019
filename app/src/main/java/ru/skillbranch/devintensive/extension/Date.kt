package ru.skillbranch.devintensive.extension

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) :Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String {
    val time = this.time
    val timeNew = date.time
    var deltaTime = timeNew - time
    //if(deltaTime < 0) return "Не могу отправится в будущее :("

        deltaTime = deltaTime / 10 * 10 // Если так не сделать не ловится значение 1 сек т.к. дельта = 1001 а не 1000

    val result = when(deltaTime){
        in  -1..SECOND -> "только что"
        in  1 * SECOND..45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND..75 * SECOND -> "минуту назад"
        in 75 * SECOND..45 * MINUTE -> "${deltaTime / MINUTE} ${timeToHumanize(deltaTime/ MINUTE,TimeUnits.MINUTE)} назад"
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in 75 * MINUTE..22 * HOUR -> "${deltaTime / HOUR} ${timeToHumanize(deltaTime/ HOUR,TimeUnits.HOUR)} назад"
        in 22 * HOUR .. 26 * HOUR ->"день назад"
        in 26 * HOUR ..360 * DAY ->"${deltaTime / DAY} ${timeToHumanize(deltaTime/ DAY,TimeUnits.DAY)} назад"
        else -> "более года назад"
    }
    return result
}

private fun timeToHumanize(second:Long,unit:TimeUnits):String {

    val workTime:Array<String>
    when(unit){
        TimeUnits.SECOND -> workTime = secondString
        TimeUnits.MINUTE -> workTime = minutsString
        TimeUnits.HOUR -> workTime = hoursString
        TimeUnits.DAY -> workTime = daysString
    }
    val workSecond:Int = (second % 100).toInt()

    val result = if (workSecond in 5..20 || workSecond == 0) workTime[2] else {
        when (workSecond % 10) {
            1 -> workTime[0]
            in 2..4 -> workTime[1]
            else -> workTime[2]
        }
    }
    return result
}
val secondString: Array<String> = arrayOf("секунду","секунды","секунд")
val minutsString: Array<String> = arrayOf("минуту","минуты","минут")
val hoursString: Array<String> = arrayOf("час","часа","часов")
val daysString: Array<String> = arrayOf("день","дня","дней")

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY,
}
