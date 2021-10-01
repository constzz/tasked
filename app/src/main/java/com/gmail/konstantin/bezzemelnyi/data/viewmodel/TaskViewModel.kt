package com.gmail.konstantin.bezzemelnyi.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.konstantin.bezzemelnyi.data.TaskDatabase
import com.gmail.konstantin.bezzemelnyi.data.model.Task
import com.gmail.konstantin.bezzemelnyi.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(app: Application) : AndroidViewModel(app) {
    private val taskDao = TaskDatabase.getInstance(app).taskDao
    private val repository = TaskRepository(taskDao)

    val allTasks = repository.allTasks

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }
}