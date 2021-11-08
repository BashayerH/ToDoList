package com.example.todolist.Database

import android.app.Application

class IntentAppToDo:Application() {
    override fun onCreate() {
        super.onCreate()
        ToDoRepo.initialize(this)
    }
}
