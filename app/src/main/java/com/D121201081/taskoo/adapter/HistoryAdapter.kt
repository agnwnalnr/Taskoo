package com.D121201081.taskoo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.D121201081.taskoo.R
import com.D121201081.taskoo.model.Task
import com.D121201081.taskoo.viewmodel.TaskViewModel


class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.TaskViewHolder>() {
    private var context: Context? = null
    private var taskList = emptyList<Task>()
    private lateinit var taskViewModel :TaskViewModel

    class TaskViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val judul:TextView = itemView.findViewById(R.id.nama_task)
        val deskripsi:TextView = itemView.findViewById(R.id.description)
        val kategori:TextView = itemView.findViewById(R.id.category)
        val moreButton:ImageButton = itemView.findViewById(R.id.popup_task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        context = parent.context
        taskViewModel = ViewModelProvider(context as FragmentActivity)[TaskViewModel::class.java]
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_task,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = taskList[position]

        holder.judul.text = currentItem.nama_tugas
        holder.deskripsi.text = currentItem.deskripsi_tugas
        holder.kategori.text = currentItem.kategori_tugas
        holder.moreButton.visibility = View.GONE
    }
    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(tugas:List<Task>){
        this.taskList = tugas
        notifyDataSetChanged()
    }
}