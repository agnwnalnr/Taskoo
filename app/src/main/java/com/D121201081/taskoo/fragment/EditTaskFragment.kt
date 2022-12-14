package com.D121201081.taskoo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.D121201081.taskoo.R
import com.D121201081.taskoo.databinding.FragmentEditTaskBinding
import com.D121201081.taskoo.model.Task
import com.D121201081.taskoo.utils.Dialog
import com.D121201081.taskoo.viewmodel.TaskViewModel

class EditTaskFragment : Fragment() {
    private var _binding: FragmentEditTaskBinding?=null
    private val binding get() = _binding!!
    private var mCheckId = 0
    private val args by navArgs<EditTaskFragmentArgs>()
    private lateinit var tugasViewModel :TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditTaskBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        binding.apply {
            judulTugas.setText(args.currentTask.nama_tugas)
            description.setText(args.currentTask.deskripsi_tugas)
            simpan.setOnClickListener {
                updateTugas(view)
            }
            back.setOnClickListener {
                findNavController().navigate(R.id.action_editTaskFragment_to_homeFragment)
            }
            batal.setOnClickListener {
                findNavController().navigate(R.id.action_editTaskFragment_to_homeFragment)
            }
        }
        var isChecking = true

        binding.row1.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ group, checkedId->
            if(checkedId!=-1&&isChecking){
                isChecking = false
                binding.row2.clearCheck()
                mCheckId = checkedId
            }
            isChecking = true
        })
        binding.row2.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ group, checkedId->
            if(checkedId!=-1&&isChecking){
                isChecking = false
                binding.row1.clearCheck()
                mCheckId = checkedId
            }
            isChecking = true
        })
        return view
    }

    private fun updateTugas(view:View){

        binding.apply {
            val kategori = view.findViewById<RadioButton>(mCheckId)
            val judul_tugas = judulTugas.text.toString()
            val isi_tugas = description.text.toString()


            if(judul_tugas.isEmpty()){
                judulTugas.error = "Masukkan judul sebelum menyimpan task"
                judulTugas.requestFocus()
                return
            }
            if(isi_tugas.isEmpty()){
                description.error = "Masukkan deskripsi sebelum menyimpan tugas"
                description.requestFocus()
                return
            }
            if(kategori.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Pilih kategori sebelum menyimpan tugas",
                    Toast.LENGTH_SHORT).show()
                return
            }
            val updateTugas = Task(args.currentTask.id, judul_tugas,isi_tugas,kategori.text.toString(),"Ongoing")
            tugasViewModel.updateTask(updateTugas)
            context?.let {
                Dialog.showDialogOkFrg(
                    it,findNavController(),R.id.action_editTaskFragment_to_homeFragment,"Tugas berhasil diubah")
            }
        }
    }
}