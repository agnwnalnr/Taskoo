package com.D121201081.taskoo.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.D121201081.taskoo.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201081.taskoo.activities.AddTaskActivity
import com.D121201081.taskoo.adapter.TaskAdapter
import com.D121201081.taskoo.viewmodel.TaskViewModel
import com.D121201081.taskoo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var tugasViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        val adapter = TaskAdapter()
        binding.taskRecycle.adapter = adapter
        binding.taskRecycle.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        tugasViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        tugasViewModel.readAllData.observe(viewLifecycleOwner){ task->
            adapter.setData(task)
            checkSize(task.size)
            textTask(task.size)
        }
        binding.addTask.setOnClickListener {
            val intent = Intent(requireContext(),AddTaskActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun checkSize(size: Int) {
        binding.apply {
            if (size<=0){
                nothingTask.visibility= View.VISIBLE
                textNothingTask.visibility = View.VISIBLE
                taskRecycle.visibility = View.GONE
            }else{
                nothingTask.visibility= View.GONE
                textNothingTask.visibility = View.GONE
                taskRecycle.visibility = View.VISIBLE
            }
        }
    }
    private fun textTask(task:Int){
        if (task==0){
            binding.jumlahtask.text = "Kamu belum memiliki task saat ini"
        }else{
            binding.jumlahtask.text = "Kamu memiliki total "+" $task "+" task saat ini"
        }
    }
}

