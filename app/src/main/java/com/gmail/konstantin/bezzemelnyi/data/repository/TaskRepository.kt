package com.gmail.konstantin.bezzemelnyi.data.repository

import androidx.lifecycle.LiveData
import com.gmail.konstantin.bezzemelnyi.data.TaskDao
import com.gmail.konstantin.bezzemelnyi.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasksByPositionDescending()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

}
