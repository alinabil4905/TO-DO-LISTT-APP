package com.example.myapplication.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.Database.model.TaskModel


@Dao
interface ToDoAppDao {

    @Insert
    suspend fun addtask(taskModel: TaskModel)

    @Query("SELECT * FROM taskmodel")
     fun getTasks(): LiveData<List<TaskModel>>

    @Update
    suspend fun updateTask(taskModel: TaskModel)

    @Delete
    suspend fun deletetask(taskModel: TaskModel)

}