package com.example.myapplication.View

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Database.model.TaskModel
import com.example.myapplication.R
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*


class EditTaskFragment : Fragment() {
    private val toDoViewModel: ToDoViewModel by activityViewModels()
    private lateinit var selectedTask: TaskModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleTextView: TextView = view.findViewById(R.id.titleupdateedittext)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionupdateedittext)
        val dateTextView: TextView = view.findViewById(R.id.updateedittextdate)
        val timePickerButton:Button = view.findViewById(R.id.timePickerupdateButton)
        val updateButton: Button = view.findViewById(R.id.updatebutton)
        val deleteButton: Button = view.findViewById(R.id.deletebutton)
        val creationDateTextView: TextView = view.findViewById(R.id.creationdatetextview)


        val datePicker = DatePickerDialog(requireActivity())
        datePicker.setOnDateSetListener { view, i, i2, i3 ->
            dateTextView.setText("$i/$i2/$i3")
        }
        dateTextView.setOnClickListener() {
            datePicker.show()
        }

        val timePickerDialog =
            MaterialTimePicker.Builder().setHour(5).setTimeFormat(TimeFormat.CLOCK_12H).build()//CLOCK_24H
        timePickerButton.setOnClickListener {
            timePickerDialog.show(requireActivity().supportFragmentManager, "fragment_tag")
        }

        toDoViewModel.selectedTaskMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { task ->
                titleTextView.text = task.headline
                descriptionTextView.text = task.description
                dateTextView.text = task.deadline


                creationDateTextView.text = "Created AT: ${task.creationdate}"

                selectedTask = task
            }
        })


        deleteButton.setOnClickListener {


            // If you want to back to the last fragment from where you come here just user the popBackStack method of NavController
            val alertDialog = AlertDialog
                .Builder(requireActivity())
                .setTitle("Delete task")
                .setMessage("Are you sure you want to delete the task?")

            alertDialog.setPositiveButton("Yes") { _, _ ->
                toDoViewModel.deleteTask(selectedTask)

            }

            alertDialog.setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }

            alertDialog.create().show()
            findNavController().popBackStack()




        }



        updateButton.setOnClickListener {

            selectedTask.headline = titleTextView.text.toString()
            selectedTask.description = descriptionTextView.text.toString()
            selectedTask.deadline = dateTextView.text.toString()

            var duetime = ""
            if(timePickerDialog.hour != 0)

                duetime = "${timePickerDialog.hour}:${timePickerDialog.minute}"
             else
                duetime = selectedTask.deadlinetime


            selectedTask.deadlinetime = duetime

            toDoViewModel.updateTask(selectedTask)
            findNavController().popBackStack()
        }

    }

}