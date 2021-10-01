package com.gmail.konstantin.bezzemelnyi.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gmail.konstantin.bezzemelnyi.data.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table")
    fun getAllTasksByPositionDescending(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

}