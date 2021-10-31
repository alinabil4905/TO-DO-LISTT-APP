package com.example.myapplication.Database

import androidx.room.*
import com.example.myapplication.Database.model.TaskModel


@Database(entities = [TaskModel::class],version = 6)
abstract class ToDoAppDatabase:RoomDatabase() {


    abstract fun todoappDao():ToDoAppDao

}