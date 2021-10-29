package com.example.myapplication.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Database.model.TaskModel
import com.example.myapplication.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ToDoListFragment : Fragment() {

private val todoTasks= mutableListOf<TaskModel>()
    private val todoViewModel:ToDoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoRecyclerView: RecyclerView = view.findViewById(R.id.todo_RecyclerView)
        val addFloatingActionButton: FloatingActionButton = view.findViewById(R.id.floatingActionButton)

        val todoappAdapter = ToDoAppAdapter(todoTasks,todoViewModel)

        todoRecyclerView.adapter = todoappAdapter

        todoViewModel.todoTasks.observe(viewLifecycleOwner, Observer {
            it?.let { tasks ->
                todoTasks.clear()
                todoTasks.addAll(tasks)
                todoappAdapter.notifyDataSetChanged()
            }
        })
        addFloatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_toDoListFragment_to_addTaskFragment2)
    }
}}