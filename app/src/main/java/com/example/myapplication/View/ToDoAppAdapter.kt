package com.example.myapplication.View

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        holder.dateTextView.text = "Done By: ${task.deadline}"
        holder.taskisdone.isChecked = task.isdone

        holder.creationdateTextView.text = "Created AT: ${task.creationdate}"
        holder.timeTextView.text = "TIME: ${task.deadlinetime}"
        holder.itemView.setOnClickListener { view ->
            viewModel.selectedTaskMutableLiveData.postValue(task)
            view.findNavController().navigate(R.id.action_toDoListFragment_to_editTaskFragment2)

        }
        var deadline = Date()
        val format = SimpleDateFormat("yyyy/MM/dd")
        val date = format.parse(task.deadline)
        if (deadline > date && !task.isdone) {
            Log.d("DEAD_LINE", task.isdone.toString())
            holder.cardView.setBackgroundColor(Color.parseColor("#731520"))
        } else if (task.isdone) {
            Log.d("IS_DONE", task.isdone.toString())

            holder.cardView.setBackgroundColor(Color.parseColor("#90A9AB"))
        } else {
            Log.d("IS_DONE", task.isdone.toString())

            holder.cardView.setCardBackgroundColor(Color.parseColor("#B6B6B6"))
        }



        if (task.isdone) {
            holder.headlineTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dateTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.descriptionTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.timeTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.taskisdone.setOnClickListener {
//            viewModel.selectedTaskMutableLiveData.postValue(task)
//            view.findNavController().navigate(R.id.action_toDoListFragment_to_editTaskFragment2)

            holder.cardView.setCardBackgroundColor(Color.parseColor("#B6B6B6"))

            if (holder.taskisdone.isChecked) {
                holder.headlineTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.dateTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.descriptionTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.timeTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.cardView.setBackgroundColor(Color.parseColor("#90A9AB"))

            } else {

                holder.headlineTextView.setPaintFlags(0)
                holder.descriptionTextView.setPaintFlags(0)
                holder.dateTextView.setPaintFlags(0)
                holder.timeTextView.setPaintFlags(0)
                holder.cardView.setBackgroundColor(Color.parseColor("#B6B6B6"))

            }

            task.isdone = holder.taskisdone.isChecked
            viewModel.updateTask(task)
        }

holder.deleteButton.setOnClickListener{
    val alertDialog = AlertDialog
        .Builder(holder.itemView.context)
        .setTitle("Delete Task")
        .setMessage("Are you sure you want to delete the task?")
    alertDialog.setPositiveButton("Yes") { _, _ ->

        viewModel.deleteTask(task)
    }

    alertDialog.setNegativeButton("No") { dialog, _ ->
        dialog.cancel()
    }

    alertDialog.create().show()

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
        val timeTextView: TextView = view.findViewById(R.id.timetextview)
        val cardView: CardView = view.findViewById(R.id.CardView)
        val creationdateTextView: TextView = view.findViewById(R.id.ceationdatetextView)
        val deleteButton: Button = view.findViewById(R.id.icondeletebutton)
    }
}