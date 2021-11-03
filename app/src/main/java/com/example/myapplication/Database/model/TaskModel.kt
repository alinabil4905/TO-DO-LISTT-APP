package com.example.myapplication.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/***
TaskModel will represent a row in a TaskModel table in the app database.
 **/
@Entity
data class TaskModel(

    var headline: String,
    var description: String,
    var deadline:String,
    var deadlinetime:String,
    var isdone: Boolean = false,
    var creationdate: String,


    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
