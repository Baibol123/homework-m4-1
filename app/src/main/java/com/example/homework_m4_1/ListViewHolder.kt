package com.example.homework_m4_1

import androidx.recyclerview.widget.RecyclerView
import com.example.homework_m4_1.databinding.ItemComplitedBinding
import com.example.homework_m4_1.databinding.ItemListBinding
import com.example.homework_m4_1.databinding.ItemProgressBinding

class ListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    var taskPosition = 0

    fun onBind(task: ListTasks) {
        with(binding) {
            itemListTask.text = task.name
            taskPosition = task.position

        }
    }
}

class ItemProgressViewHolder(private val binding: ItemProgressBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var taskPosition = 0
    fun onBind(task: ListTasks) {
        binding.itemListTask.text = task.name
        taskPosition = task.position
    }
}

class ItemComplitedViewHolder(private val binding: ItemComplitedBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var taskPosition = 0
    fun onBind(task: ListTasks) {
        binding.itemListTask.text = task.name
        taskPosition = task.position
    }
}