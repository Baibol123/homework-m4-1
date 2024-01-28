package com.example.homework_m4_1.ui.tasks.viewpager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework_m4_1.ListAdapter
import com.example.homework_m4_1.ListTasks
import com.example.homework_m4_1.databinding.FragmentProgressBinding


class ProgressFragment(
    private val tasks: ArrayList<ListTasks>,
    private val onItemClick: (position: Int) -> Unit,
) : Fragment() {
    private var _binding: FragmentProgressBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskList.adapter = ListAdapter(tasks, onItemClick)
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.taskList.adapter?.notifyDataSetChanged()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}