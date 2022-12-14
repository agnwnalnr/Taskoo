package com.D121201081.taskoo.fragmento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201081.taskoo.R
import com.D121201081.taskoo.adapter.HistoryAdapter
import com.D121201081.taskoo.databinding.FragmentHistoryBinding
import com.D121201081.taskoo.utils.Dialog
import com.D121201081.taskoo.viewmodel.TaskViewModel

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding?=null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        val view = binding.root

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val adapter = HistoryAdapter()
        binding.taskrecycle.adapter = adapter
        binding.taskrecycle.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.readAllDataHistory.observe(viewLifecycleOwner){ task->
            adapter.setData(task)
            checkSize(task.size)
        }
        binding.clearhistory.setOnClickListener {
            Dialog.showDialog2Button(
                requireContext(),
                findNavController(),
                resources.getString(R.string.historyDelete),
                0,
                taskViewModel,
                2,
                0,
                "",
                "",
                ""
            )
        }
        return view
    }

    private fun checkSize(size: Int) {
        binding.apply {
            if (size<=0){
                historykosong.visibility= View.VISIBLE
                taskrecycle.visibility = View.GONE
            }else{
                historykosong.visibility= View.GONE
                taskrecycle.visibility = View.VISIBLE
            }
        }
    }
}