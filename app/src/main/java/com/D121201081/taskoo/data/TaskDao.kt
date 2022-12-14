package com.D121201081.taskoo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.D121201081.taskoo.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task WHERE status='Ongoing' ORDER BY id ASC")
    fun readAllData():LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE status='Completed'")
    fun readAllDataHistory():LiveData<List<Task>>
    @Update
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task WHERE status='Completed'")
    suspend fun deleteHistory()
}