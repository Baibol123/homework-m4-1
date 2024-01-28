package com.example.homework_m4_1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework_m4_1.ListTasks
import com.example.homework_m4_1.R
import com.example.homework_m4_1.TypeTasks
import com.example.homework_m4_1.databinding.FragmentTaskEditBinding
import com.example.homework_m4_1.ui.home.viewpager.HomeFragmentArgs


class TaskEditFragment : Fragment() {


    private var _binding: FragmentTaskEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var args: TaskEditFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args = TaskEditFragmentArgs.fromBundle(it) }
        initListeners()
        initView()
    }
    private fun initView() {
        binding.taskAddDes.setText(args.argtask.name)
        when (args.argtask.type) {
            TypeTasks.LIST -> binding.rbList.isChecked = true
            TypeTasks.IN_PROGRESS -> binding.rbProgress.isChecked = true
            TypeTasks.COMPLETED -> binding.rbCompleted.isChecked = true
        }
    }
    private fun initListeners() {
        binding.saveButton.setOnClickListener {
            val taskDescription = binding.taskAddDes.text.toString()
            val position = args.argtask.position
            val taskType = when (binding.rgCategory.checkedRadioButtonId) {
                binding.rbList.id -> TypeTasks.LIST
                binding.rbCompleted.id -> TypeTasks.COMPLETED
                else -> TypeTasks.IN_PROGRESS
            }

            val updatedTask = ListTasks(name = taskDescription, position = position, taskType)
            val newTaskFragmentArgs = TaskEditFragmentArgs(updatedTask, args.addtaskarg, args.typearg).toBundle()

            setFragmentResult(KEY1, newTaskFragmentArgs)
            findNavController().navigateUp()
        }
    }

    companion object {
        const val KEY1 = "KEY"
    }
}