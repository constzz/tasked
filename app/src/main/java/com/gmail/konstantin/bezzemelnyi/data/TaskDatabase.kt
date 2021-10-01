package com.gmail.konstantin.bezzemelnyi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.konstantin.bezzemelnyi.data.model.Task

@Database(entities = [Task::class], exportSchema = false, version = 2)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDao: TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room
                            .databaseBuilder(
                            context.applicationContext,
                            TaskDatabase::class.java,
                            "task_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return INSTANCE as TaskDatabase
            }
        }
    }

}