package com.example.todolist.EditFromList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.todolist.Database.ToDoData
import com.example.todolist.Database.ToDoRepo
import java.util.*

class EditFromListViewModel : ViewModel() {


    private val noteRepo= ToDoRepo.get()
    private val noteIdLiveData= MutableLiveData<UUID>()

    var noteLiveData: LiveData<ToDoData?> = Transformations.switchMap(noteIdLiveData) {
        noteRepo.getlistById(it)
    }
    fun saveUpdate(note:ToDoData){
        noteRepo.updateList(note)
    }

    fun loadToDoList(noteId:UUID){
        noteIdLiveData.value=noteId
    }
    fun addtodo(note: ToDoData){
        noteRepo.insertList(note)
    }
    fun deleteToDo(note: ToDoData){
        noteRepo.deleteList(note)
    }
}