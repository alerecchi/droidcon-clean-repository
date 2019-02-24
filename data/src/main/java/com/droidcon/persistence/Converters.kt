package com.droidcon.persistence

import androidx.room.TypeConverter
import java.util.*


object Converters { //TODO: check this converter  https://medium.com/androiddevelopers/room-time-2b4cf9672b98
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return (date?.time)?.toLong()
    }
}