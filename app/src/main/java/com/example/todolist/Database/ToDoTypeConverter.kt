package com.example.todolist.Database

import androidx.room.TypeConverter
import java.util.*

class ToDoTypeConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let { Date(it) }
    }
    @TypeConverter
    fun fromUUId(id: UUID?): String? {
        return id?.toString()
    }
    @TypeConverter
    fun toUUID(id: String?): UUID? {
        return UUID.fromString(id)
    }

}