package com.example.myapplication.View

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class AddTaskFragment : Fragment() {

    private val todoViewModel: ToDoViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleEditText: EditText = view.findViewById(R.id.titleaddedittext)
        val descriptionEditText: EditText = view.findViewById(R.id.descriptionaddedittext)
        val dateTextView: EditText = view.findViewById(R.id.addedittextdate)
        val saveButton: Button = view.findViewById(R.id.savebutton)

        val datePicker = DatePickerDialog(requireActivity())
        datePicker.setOnDateSetListener { view, i, i2, i3 ->
            dateTextView.setText("$i/${i2+1}/$i3")
        }
        dateTextView.setOnClickListener(){
            datePicker.show()
        }
        var format = SimpleDateFormat("yyyy/mm/dd")
        var dateToday = format.format(Date())
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val duedate = dateTextView.text.toString()
            val createdTask = dateToday
            if (title.isNotEmpty() && description.isNotEmpty() && duedate.isNotEmpty()) {
                todoViewModel.addTask(title, description, duedate,false,createdTask)
                findNavController().popBackStack()
            }
        }
    }

}