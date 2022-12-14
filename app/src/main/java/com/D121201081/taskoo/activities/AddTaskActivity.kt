package com.D121201081.taskoo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.D121201081.taskoo.databinding.ActivityAddtaskBinding
import com.D121201081.taskoo.model.Task
import com.D121201081.taskoo.utils.Dialog
import com.D121201081.taskoo.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddtaskBinding
    private lateinit var taskViewModel: TaskViewModel
    private var mCheckId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddtaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        var isChecking = true

        binding.row1.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{group,checkedId->
            if(checkedId!=-1&&isChecking){
                isChecking = false
                binding.row2.clearCheck()
                mCheckId = checkedId
            }
            isChecking = true
        })
        binding.row2.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{group,checkedId->
            if(checkedId!=-1&&isChecking){
                isChecking = false
                binding.row1.clearCheck()
                mCheckId = checkedId
            }
            isChecking = true
        })
        onAction()
    }
    private fun onAction(){
        binding.apply {
            back.setOnClickListener {
                finish()
            }
            buat.setOnClickListener {
                sendToDB()
            }
            batal.setOnClickListener{
                finish()
            }
        }
    }
    private fun sendToDB(){
        binding.apply {
            val kategori = findViewById<RadioButton>(mCheckId)
            val judul = judulTask.text.toString()
            val deskripsi = deskripsiTask.text.toString()

            if(judul.isEmpty()){
                judulTask.error = "Masukkan judul sebelum membuat task"
                judulTask.requestFocus()
                return
            }
            if(deskripsi.isEmpty()){
                deskripsiTask.error = "Masukkan deskripsi sebelum membuat task"
                deskripsiTask.requestFocus()
                return
            }
            if(kategori.text.toString().isEmpty()){
                Toast.makeText(this@AddTaskActivity,"Pilih kategori sebelum membuat task",
                    Toast.LENGTH_SHORT).show()
                return
            }

            lifecycleScope.launch{
                val task = Task(0,judul,deskripsi,kategori.text.toString(), "Ongoing")
                taskViewModel.addTask(task)
                Dialog.showDialogOkAct(this@AddTaskActivity,"Task berhasil ditambahkan")
            }
        }
    }
}