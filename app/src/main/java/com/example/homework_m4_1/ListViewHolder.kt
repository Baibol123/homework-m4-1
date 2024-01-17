package com.example.homework_m4_1

import androidx.recyclerview.widget.RecyclerView
import com.example.homework_m4_1.databinding.ItemListBinding

class ListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(task: ListTasks) {
        with(binding) {
            itemListTask.text = task.name

        }
    }
}