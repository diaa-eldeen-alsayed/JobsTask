package com.example.jobstask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobstask.databinding.JobListItemBinding
import com.example.jobstask.model.Job
import com.example.jobstask.model.JobItem

class JobAdapter(private val items: Job) :RecyclerView.Adapter<JobAdapter.JobViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JobListItemBinding.inflate(inflater)
        return JobViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
       holder.bind(items[position])
    }
    inner class JobViewHolder(private val binding: JobListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JobItem) {
          binding.jobItem=item
        }
    }

}