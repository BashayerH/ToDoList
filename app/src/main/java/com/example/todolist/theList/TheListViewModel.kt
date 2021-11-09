package com.example.todolist.theList

import androidx.lifecycle.ViewModel
import com.example.todolist.Database.ToDoData
import com.example.todolist.Database.ToDoRepo
import java.util.*

class TheListViewModel:ViewModel() {


    private val noteREpo=ToDoRepo.get()
    val noteLiveData=noteREpo.getAllList()

    fun insertList(note: ToDoData){
        noteREpo.insertList(note)
    }

    fun loadToDoList(noteId: UUID){
    noteLiveData.value
    }
}