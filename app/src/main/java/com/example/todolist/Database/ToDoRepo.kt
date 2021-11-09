package com.example.todolist.Database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val  NAME_OF_DATABASE= "database_name"

class ToDoRepo private constructor(context: Context) {

    private val database: ToDoDatabase = Room.databaseBuilder(
        context.applicationContext,
        ToDoDatabase::class.java,
        NAME_OF_DATABASE
    ).build()

    private val noteDAO = database.toDoDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getAllList(): LiveData<List<ToDoData>> = noteDAO.getAllList()

    fun getlistById(id: UUID): LiveData<ToDoData?> {

        return noteDAO.getlistById(id)
    }


    fun updateList(note: ToDoData) {
        executor.execute {
            noteDAO.updateList(note)
        }
    }

    fun insertList(note: ToDoData){
        executor.execute {
            noteDAO.insertList(note)
        }
    }

    fun deleteList(note: ToDoData){
        executor.execute {
            noteDAO.deleteList(note)
        }
    }

    companion object{
        var INSTANCE:ToDoRepo?=null

        fun initialize(context: Context){
            if(INSTANCE==null){
                INSTANCE=ToDoRepo(context)
            }
        }
        fun get():ToDoRepo{
            return INSTANCE?: throw IllegalStateException("repo must be initialize")
        }
    }
}