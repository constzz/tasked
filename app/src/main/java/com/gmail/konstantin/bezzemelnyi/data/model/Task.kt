package com.gmail.konstantin.bezzemelnyi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    var content: String,
    var checked: Boolean = false,
    var position: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)