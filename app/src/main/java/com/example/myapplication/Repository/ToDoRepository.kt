package com.example.myapplication.Repository

import android.content.Context
import androidx.room.Room
import com.example.myapplication.Database.ToDoAppDatabase
import com.example.myapplication.Database.model.TaskModel
import java.lang.Exception


private const val DATABASE_NAME = "todo-database"
class ToDoRepository(context: Context) {

private val database:ToDoAppDatabase=
    Room.databaseBuilder(
        context,
        ToDoAppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

private val todoappDao= database.todoappDao()
//  gettask fun will give you the list
fun getTasks()=todoappDao.getTasks()

// addtask fun will allow you to add one to a list tasks
    suspend fun addTask(taskModel: TaskModel) = todoappDao.addtask(taskModel)
    // updateTask fun will allow you to update item saved
    suspend fun updateTask(taskModel: TaskModel) = todoappDao.updateTask(taskModel)

    suspend fun deleteTask(taskModel: TaskModel) = todoappDao.deletetask(taskModel)


    companion object {
        private var instance: ToDoRepository? = null

        fun init(context: Context) {
            if (instance == null)
                instance = ToDoRepository(context)
        }

        fun get(): ToDoRepository {
            return instance ?: throw Exception("ToDo Repository must be initialized")
        }
    }
}