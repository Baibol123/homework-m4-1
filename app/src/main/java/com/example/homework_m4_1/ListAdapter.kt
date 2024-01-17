package com.example.homework_m4_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_m4_1.databinding.ItemListBinding

class ListAdapter(
    private val tasks: ArrayList<ListTasks>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.onBind(tasks[position])

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }
}

