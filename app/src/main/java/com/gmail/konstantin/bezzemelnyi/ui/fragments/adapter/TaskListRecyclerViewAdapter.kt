package com.gmail.konstantin.bezzemelnyi.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmail.konstantin.bezzemelnyi.common.extensions.hideKeyboard
import com.gmail.konstantin.bezzemelnyi.common.extensions.setMultilineActionInputType
import com.gmail.konstantin.bezzemelnyi.common.extensions.setOnEditorActionDone
import com.gmail.konstantin.bezzemelnyi.common.extensions.strike
import com.gmail.konstantin.bezzemelnyi.data.model.Task
import com.gmail.konstantin.bezzemelnyi.databinding.RowLayoutTaskBinding

class TaskListRecyclerViewAdapter(private val updateTask: (task: Task) -> Unit) :
    RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskViewHolder>() {

    private var data = emptyList<Task>()

    class TaskViewHolder(private var binding: RowLayoutTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                return TaskViewHolder(
                    RowLayoutTaskBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(task: Task, updateTask: (task: Task) -> Unit) {
            with(binding) {
                this.taskItem = task

                checkbox.setOnClickListener {
                    updateTask(task.apply { this.checked = !this.checked })
                    contentEt.strike = task.checked
                }

                with(contentEt) {
                    strike = task.checked
                    setOnEditorActionDone {
                        if (this.text.toString().isNotEmpty()) {
                            updateTask(task.apply {
                                this.content = contentEt.text.toString()
                            })
                        } else {
                            this.setText(task.content)
                        }
                    }
                    setMultilineActionInputType()
                }

                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position], updateTask)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(tasks: List<Task>) {
        val toDoDiffUtil = TaskDiffUtil(this.data, tasks)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.data = tasks
        toDoDiffResult.dispatchUpdatesTo(this)
    }

    fun getTask(adapterPosition: Int): Task {
        return data[adapterPosition]
    }

}