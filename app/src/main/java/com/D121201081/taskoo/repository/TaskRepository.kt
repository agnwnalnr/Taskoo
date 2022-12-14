package com.D121201081.taskoo.repository

import androidx.lifecycle.LiveData
import com.D121201081.taskoo.data.TaskDao
import com.D121201081.taskoo.model.Task

class TaskRepository (private val taskDao: TaskDao){
    val readAllData:LiveData<List<Task>> = taskDao.readAllData()
    val readAllDataHistory:LiveData<List<Task>> = taskDao.readAllDataHistory()
    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }
    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
    suspend fun deleteHistory(){
        taskDao.deleteHistory()
    }
}