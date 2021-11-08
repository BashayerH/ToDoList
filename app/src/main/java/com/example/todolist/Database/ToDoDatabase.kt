package com.example.todolist.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [ToDoData::class],version = 1)
@TypeConverters(ToDoTypeConverter::class)
abstract class ToDoDatabase:RoomDatabase(){

    abstract fun toDoDao():ToDoDAO//ليييش
}
