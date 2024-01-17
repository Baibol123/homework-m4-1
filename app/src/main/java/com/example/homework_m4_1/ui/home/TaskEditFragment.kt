package com.example.homework_m4_1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.homework_m4_1.databinding.FragmentTaskEditBinding


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
        binding.taskAddDes.setText(args.argtext)
    }
    private fun initListeners() {
        binding.saveButton.setOnClickListener {
            val taskDescription = binding.taskAddDes.text.toString()

            setFragmentResult(
                KEY1, HomeFragmentArgs(
                argtext = taskDescription,
                position = args.position,
            ).toBundle())

            findNavController().navigateUp()
        }
    }

    companion object {
        const val KEY1 = "KEY"
    }
}