package com.D121201081.taskoo.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import com.D121201081.taskoo.R
import com.D121201081.taskoo.model.Task
import com.D121201081.taskoo.viewmodel.TaskViewModel

object Dialog {
    fun showDialogOkAct(context:Context,contentDialog:String) {
        val view = View.inflate(context, R.layout.dialog_success,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val dialog = builder.create()
        val button_ok = view.findViewById<Button>(R.id.button_ok)
        val content = view.findViewById<TextView>(R.id.content)

        content.text = contentDialog
        dialog.show()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        button_ok.setOnClickListener {
            dialog.dismiss()
            (context as Activity).finish()
        }
    }
    fun showDialogOkFrg(context: Context,nav:NavController,destination:Int,contentDialog:String) {
        val view = View.inflate(context, R.layout.dialog_success,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val dialog = builder.create()
        val button_ok = view.findViewById<Button>(R.id.button_ok)
        val content = view.findViewById<TextView>(R.id.content)

        content.text = contentDialog
        dialog.show()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        button_ok.setOnClickListener {
            dialog.dismiss()
            nav.navigate(destination)
        }
    }
    fun showDeleteDialog(context:Context, contentDialog:String,taskViewModel: TaskViewModel,task: Task){
        val view = View.inflate(context,R.layout.dialog_option,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        val yakin = view.findViewById<Button>(R.id.yakin)
        val batal = view.findViewById<Button>(R.id.batal)
        val judul = view.findViewById<TextView>(R.id.content)

        judul.text = contentDialog
        dialog.show()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        yakin.setOnClickListener {
            taskViewModel.deleteTask(task)
            Toast.makeText(context,"Task telah dihapus",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        batal.setOnClickListener {
            dialog.dismiss()
        }
    }
    fun showDialog2Button(context: Context,nav: NavController,contentDialog:String,destination:Int,taskViewModel: TaskViewModel,kode:Int,idTask:Int,judulTask:String,deskripsiTask:String,kategoriTask:String){
        val view = View.inflate(context,R.layout.dialog_option,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        val yakin = view.findViewById<Button>(R.id.yakin)
        val batal = view.findViewById<Button>(R.id.batal)
        val content = view.findViewById<TextView>(R.id.content)

        content.text = contentDialog
        dialog.show()
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        yakin.setOnClickListener {
            //kode = 1 -> update, 2->delete
            if (kode==1){
                val updateTask = Task(idTask,judulTask,deskripsiTask,kategoriTask,"Selesai")
                taskViewModel.updateTask(updateTask)
                nav.navigate(destination)
            }else{
                taskViewModel.deleteHistory()
                Toast.makeText(context,"History dibersihkan",Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }
        batal.setOnClickListener {
            dialog.dismiss()
        }
    }
}