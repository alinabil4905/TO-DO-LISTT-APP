package com.example.myapplication.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class TaskModel(

    val headline:String,
    val description:String,
    var date: String,
    var taskdone:Boolean,





    @PrimaryKey(autoGenerate=true)
    val id:Int=0
)
