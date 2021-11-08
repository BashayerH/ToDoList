package com.example.todolist.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class ToDoData(
    @PrimaryKey val id:UUID=UUID.randomUUID(),
    var textedit:String=" ",
    var date:Date =Date(),
    var isDone:Boolean=false
)



