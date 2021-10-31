package com.example.myapplication.View

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Database.model.TaskModel
import com.example.myapplication.R
import java.text.SimpleDateFormat
import java.util.*

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
        val task = tasks[position]
        holder.headlineTextView.text = task.headline
        holder.descriptionTextView.text = task.description
        holder.dateTextView.text = task.deadline
        holder.taskisdone.isChecked = task.isdone
        holder.itemView.setOnClickListener { view ->
            viewModel.selectedTaskMutableLiveData.postValue(task)
            view.findNavController().navigate(R.id.action_toDoListFragment_to_editTaskFragment2)

        }
        var deadline=Date()
        val format=SimpleDateFormat("yyyy/MM/dd")
        val date=format.parse(task.deadline)
        if (deadline>date){
            holder.cardView.setBackgroundColor(Color.parseColor("#FFC80F0F"))
        }

        if(task.isdone){
            holder.headlineTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dateTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.descriptionTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.taskisdone.setOnClickListener {
//            viewModel.selectedTaskMutableLiveData.postValue(task)
//            view.findNavController().navigate(R.id.action_toDoListFragment_to_editTaskFragment2)

            if(holder.taskisdone.isChecked)
            {
                holder.headlineTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.dateTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.descriptionTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }
            else
            {
                holder.headlineTextView.setPaintFlags(0)
                holder.descriptionTextView.setPaintFlags(0)
                holder.dateTextView.setPaintFlags(0)
            }

            task.isdone = holder.taskisdone.isChecked
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
        val taskisdone: CheckBox = view.findViewById(R.id.checkbox)
val cardView:CardView=view.findViewById(R.id.CardView)

    }
}