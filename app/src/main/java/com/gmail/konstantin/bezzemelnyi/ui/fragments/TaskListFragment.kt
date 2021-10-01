package com.gmail.konstantin.bezzemelnyi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.konstantin.bezzemelnyi.common.extensions.onItemHorizontalSwipe
import com.gmail.konstantin.bezzemelnyi.data.model.Task
import com.gmail.konstantin.bezzemelnyi.data.viewmodel.TaskViewModel
import com.gmail.konstantin.bezzemelnyi.databinding.FragmentTaskListBinding
import com.gmail.konstantin.bezzemelnyi.ui.bottomsheet.TaskAddBottomSheet
import com.gmail.konstantin.bezzemelnyi.ui.fragments.adapter.TaskListRecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()

    private val taskListAdapter: TaskListRecyclerViewAdapter
            by lazy { TaskListRecyclerViewAdapter((taskViewModel::updateTask)) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater)

        setupRecyclerView(binding.taskListRv)
        setupFab(binding.addTaskFab)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupFab(floatingActionButton: FloatingActionButton) {
        floatingActionButton.apply {
            setOnClickListener {
                TaskAddBottomSheet.newInstance(
                    Bundle(),
                    ::addTask
                ).show(childFragmentManager, "ADD_TASK_DIALOG")
            }

        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                binding.root.context, LinearLayoutManager.VERTICAL, false
            )
            adapter = taskListAdapter

            //TODO implement Drag and Drop functionality

            onItemHorizontalSwipe { holderAdapterPosition ->
                taskViewModel.deleteTask(
                    taskListAdapter.getTask(holderAdapterPosition)
                )
                taskListAdapter.notifyItemRemoved(holderAdapterPosition)
            }

            taskViewModel.allTasks.observe(viewLifecycleOwner, {
                taskListAdapter.setData(it)
                isDatabaseEmpty(it.isEmpty())
            })
        }
    }

    private fun addTask(content: String) {
        if (content.isNotEmpty()) {
            taskViewModel.addTask(
                Task(
                    content,
                    position = taskListAdapter.itemCount
                )
            )
        }
    }

    private fun isDatabaseEmpty(boolean: Boolean) {
        binding.isDatabaseEmpty = boolean
    }

}