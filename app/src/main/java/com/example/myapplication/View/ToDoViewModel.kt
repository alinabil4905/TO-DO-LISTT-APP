package com.example.myapplication.View

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.model.TaskModel
import com.example.myapplication.Repository.ToDoRepository
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class ToDoViewModel:ViewModel() {

    private val todorepository=ToDoRepository.get()
    var todoTasks=todorepository.getTasks()
    var task:TaskModel?=null
    var selectedTaskMutableLiveData= MutableLiveData<TaskModel>()

    fun addTask(headline:String,description:String,date:String,taskdone:Boolean){
        viewModelScope.launch {
            todorepository.addTask(TaskModel(headline,description,date,taskdone))
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