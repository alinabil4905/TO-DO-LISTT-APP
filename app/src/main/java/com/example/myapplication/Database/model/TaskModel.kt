package com.example.myapplication.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


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
