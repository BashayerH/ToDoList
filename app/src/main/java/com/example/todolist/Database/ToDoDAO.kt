package com.example.todolist.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao



interface ToDoDAO {
    @Query("SELECT * FROM ToDoData")
    fun getAllList():LiveData<List<ToDoData>>

  @Query("SELECT*FROM ToDoData WHERE id =(:id)")
   fun getlistById(id:UUID):LiveData<ToDoData?>

   @Update
   fun updateList(note:ToDoData)

   @Insert
   fun insertList(note: ToDoData)

   @Delete
   fun deleteList(note: ToDoData)
}