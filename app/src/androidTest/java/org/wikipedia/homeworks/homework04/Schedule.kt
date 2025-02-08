package org.wikipedia.homeworks.homework04

import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class ScheduleEntity(val lesson: String, val startTime: LocalTime, val endTime: LocalTime)

enum class Days {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

class Schedule {
    private val scheduleOfWeek = mutableMapOf<Days, MutableList<ScheduleEntity>>()
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private var currentDay: Days? = null

    private fun addSchedule(day: Days, scheduleEntity: ScheduleEntity) {
        scheduleOfWeek.getOrPut(day) { mutableListOf() }.add(scheduleEntity)
    }

    override fun toString(): String {
        return scheduleOfWeek.toSortedMap()
            .map { (day, list) ->
                list.sortedBy { it.startTime }
                    .joinToString("\n") {
                        "%-15s${it.startTime.format(timeFormatter)} - ${
                            it.endTime.format(
                                timeFormatter
                            )
                        }".format("\t${it.lesson}:")
                    }.let {
                        "${day.name.lowercase().replaceFirstChar { day.name[0].uppercase() }}:\n$it"
                    }
            }.joinToString("\n\n")
    }

    // DSL методы
    fun monday(function: Schedule.() -> Unit) {
        currentDay = Days.MONDAY
        function()
        currentDay = null
    }

    fun tuesday(function: Schedule.() -> Unit) {
        currentDay = Days.TUESDAY
        function()
        currentDay = null
    }

    fun wednesday(function: Schedule.() -> Unit) {
        currentDay = Days.WEDNESDAY
        function()
        currentDay = null
    }

    fun thursday(function: Schedule.() -> Unit) {
        currentDay = Days.THURSDAY
        function()
        currentDay = null
    }

    fun friday(function: Schedule.() -> Unit) {
        currentDay = Days.FRIDAY
        function()
        currentDay = null
    }

    fun saturday(function: Schedule.() -> Unit) {
        currentDay = Days.SATURDAY
        function()
        currentDay = null
    }

    fun sunday(function: Schedule.() -> Unit) {
        currentDay = Days.SUNDAY
        function()
        currentDay = null
    }

    // Метод для поддержки DSL синтаксиса с временным диапазоном
    infix fun TimeRange.schedule(lesson: String) {
        if (currentDay != null) {
            addSchedule(currentDay!!, ScheduleEntity(lesson, this.startTime, this.endTime))
        }
    }

    // Оператор invoke внутри класса
    operator fun invoke(function: Schedule.() -> Unit) {
        function()
    }
}

// Вспомогательный класс для работы с временным диапазоном
class TimeRange(val startTime: LocalTime, val endTime: LocalTime)

// Оператор расширения для String для создания LocalTime
operator fun String.rangeTo(other: String): TimeRange {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return TimeRange(
        LocalTime.parse(this, formatter),
        LocalTime.parse(other, formatter)
    )
}

fun main() {

    val schedule = Schedule()

    schedule {

        monday {
            "10:30".."11:10" schedule "Biology"
            "11:15".."11:55" schedule "Chemistry"
            "09:00".."09:40" schedule "Mathematics"
            "09:45".."10:25" schedule "History"
        }

        tuesday {
            "09:00".."09:40" schedule "English"
            "09:45".."10:25" schedule "Geography"
            "11:15".."11:55" schedule "Art"
            "10:30".."11:10" schedule "Physics"
        }

        wednesday {
            "11:15".."11:55" schedule "Biology"
            "09:00".."09:40" schedule "Literature"
            "10:30".."11:10" schedule "History"
            "09:45".."10:25" schedule "Mathematics"
        }

        thursday {
            "11:15".."11:55" schedule "Physics"
            "10:30".."11:10" schedule "Geography"
            "09:00".."09:40" schedule "Chemistry"
            "09:45".."10:25" schedule "English"
        }

        friday {
            "09:45".."10:25" schedule "Literature"
            "11:15".."11:55" schedule "History"
            "09:00".."09:40" schedule "Art"
            "10:30".."11:10" schedule "Mathematics"
        }
    }

    println(schedule.toString())
}
