package com.example.todolist.theList

import androidx.lifecycle.ViewModel
import com.example.todolist.Database.ToDoData
import com.example.todolist.Database.ToDoRepo

class TheListViewModel:ViewModel() {


    val noteREpo=ToDoRepo.get()
    val noteLiveData=noteREpo.getAllList()

    fun insertList(note: ToDoData){
        noteREpo.insertList(note)
    }
}