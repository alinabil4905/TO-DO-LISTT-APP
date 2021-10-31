package com.example.myapplication.View

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.model.TaskModel
import com.example.myapplication.Repository.ToDoRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ToDoViewModel:ViewModel() {

    private val todorepository=ToDoRepository.get()
    var todoTasks=todorepository.getTasks()
    var task:TaskModel?=null
    var selectedTaskMutableLiveData= MutableLiveData<TaskModel>()

    fun addTask(headline:String,description:String,deadline:String,isDone:Boolean,creationdate:String){


        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val currentDate = dateFormat.format(Date())
        viewModelScope.launch {
            todorepository.addTask(TaskModel(headline,description,deadline, false,currentDate))
        }
    }
    fun updateTask(taskModel: TaskModel){
        viewModelScope.launch {
            todorepository.updateTask(taskModel)
        }
    }

    fun deleteTask(taskModel: TaskModel){
        viewModelScope.launch {
            todorepository.deleteTask(taskModel)
        }
    }




}