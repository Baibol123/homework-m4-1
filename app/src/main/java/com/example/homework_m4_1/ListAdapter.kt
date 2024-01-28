package com.example.homework_m4_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework_m4_1.databinding.ItemComplitedBinding
import com.example.homework_m4_1.databinding.ItemListBinding
import com.example.homework_m4_1.databinding.ItemProgressBinding

class ListAdapter(
    private var tasks: List<ListTasks>,
    private val onItemClick: (position: Int) -> Unit

) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TypeTasks.LIST.id ->
                ListViewHolder(ItemListBinding.inflate(inflater, parent, false))

            TypeTasks.IN_PROGRESS.id ->
                ItemProgressViewHolder(ItemProgressBinding.inflate(inflater, parent, false))

            else ->
                ItemComplitedViewHolder(ItemComplitedBinding.inflate(inflater, parent, false))
        }

    }

    override fun getItemViewType(position: Int): Int {
        return tasks[position].type.id
    }

    override fun getItemCount(): Int = tasks.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ItemProgressViewHolder -> holder.onBind(tasks[position])
            is ListViewHolder -> holder.onBind(tasks[position])
            else -> (holder as ItemComplitedViewHolder).onBind(tasks[position])
        }
        holder.itemView.setOnClickListener {
            onItemClick(tasks[position].position)
        }
    }
}



