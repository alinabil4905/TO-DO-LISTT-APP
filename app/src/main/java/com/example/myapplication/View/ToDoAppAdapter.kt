package com.example.myapplication.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Database.model.TaskModel
import com.example.myapplication.R

class ToDoAppAdapter(val tasks: List<TaskModel>, val viewModel: ToDoViewModel) :
    RecyclerView.Adapter<ToDoAppAdapter.ToDoViewHodler>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToDoAppAdapter.ToDoViewHodler {
        return ToDoViewHodler(
            LayoutInflater.from(parent.context).inflate(
                R.layout.task_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ToDoAppAdapter.ToDoViewHodler, position: Int) {
        val task=tasks[position]
holder.headlineTextView.text=task.headline
        holder.descriptionTextView.text=task.description
        holder.dateTextView.text=task.date
        holder.taskdone.isChecked=task.taskdone
        holder.itemView.setOnClickListener{ view ->
viewModel.selectedTaskMutableLiveData.postValue(task)
    view.findNavController().navigate(R.id.action_toDoListFragment_to_editTaskFragment2)

        }
holder.taskdone.setOnClickListener{
task.taskdone=holder.taskdone.isChecked
viewModel.updateTask(task)
}

    }


    override fun getItemCount(): Int {
        return tasks.size
    }


    class ToDoViewHodler(view: View) : RecyclerView.ViewHolder(view) {
        val headlineTextView: TextView = view.findViewById(R.id.titletextview)
        val descriptionTextView: TextView = view.findViewById(R.id.descrioptiontextview)
        val dateTextView: TextView = view.findViewById(R.id.datetextview)
        val taskdone: CheckBox = view.findViewById(R.id.checkbox)


    }
}