package com.D121201081.taskoo.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.D121201081.taskoo.R
import com.D121201081.taskoo.fragment.HomeFragmentDirections
import com.D121201081.taskoo.model.Task
import com.D121201081.taskoo.utils.Dialog
import com.D121201081.taskoo.viewmodel.TaskViewModel


class TaskAdapter:RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var context: Context? = null
    private var tugasList = emptyList<Task>()
    private lateinit var tugasViewModel :TaskViewModel

    class TaskViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val judul:TextView = itemView.findViewById(R.id.nama_task)
        val deskripsi:TextView = itemView.findViewById(R.id.description)
        val kategori:TextView = itemView.findViewById(R.id.category)
        val moreButton:ImageButton = itemView.findViewById(R.id.popup_task)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        context = parent.context
        tugasViewModel = ViewModelProvider(context as FragmentActivity)[TaskViewModel::class.java]
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_task,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = tugasList[position]

        holder.judul.text = currentItem.nama_tugas
        holder.deskripsi.text = currentItem.deskripsi_tugas
        holder.kategori.text = currentItem.kategori_tugas

        holder.moreButton.setOnClickListener {
            moreOptions(it,position,holder)
        }
    }
    override fun getItemCount(): Int {
        return tugasList.size
    }

    fun setData(tugas:List<Task>){
        this.tugasList = tugas
        notifyDataSetChanged()
    }

    private fun moreOptions(v:View,position: Int,holder: TaskViewHolder){
        val popupMenus = PopupMenu(context,v)
        popupMenus.inflate(R.menu.popup_task)
        popupMenus.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.selesaikan_tugas->{
                    val done = Task(tugasList[position].id,tugasList[position].nama_tugas,tugasList[position].deskripsi_tugas,tugasList[position].kategori_tugas,"Completed")
                    tugasViewModel.updateTask(done)
//                    val action = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(tugasList[position])
//                    holder.itemView.findNavController().navigate(action)
                    true
                }
                R.id.edit_tugas->{
                    val action = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(tugasList[position])
                    holder.itemView.findNavController().navigate(action)
                    true
                }
                R.id.hapus_tugas->{
                    Dialog.showDeleteDialog(context!!,"Hapus tugas?",tugasViewModel,tugasList[position])
                    true
                }
                else->true
            }
        }
        popupMenus.show()
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenus)
        menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(menu,true)
    }

}