package com.example.jobstask.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.jobstask.R
import com.example.jobstask.databinding.JobListItemBinding

import com.example.jobstask.interfaces.ClickHandlers
import com.example.jobstask.model.JobItem
import com.example.jobstask.view.JobDetailsFragment
import com.example.jobstask.viewmodel.JobsViewModel
import timber.log.Timber

class JobAdapter(private val items: List<JobItem>, private val jobsViewModel: JobsViewModel) :RecyclerView.Adapter<JobAdapter.JobViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JobListItemBinding.inflate(inflater,parent,false)
        return JobViewHolder(binding,jobsViewModel)
    }

    override fun getItemCount(): Int {
       return items.size
    }


    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
       holder.bind(items[position])
    }
    inner class JobViewHolder(private val binding: JobListItemBinding, private val jobsViewModel: JobsViewModel) : RecyclerView.ViewHolder(binding.root) ,ClickHandlers.JobListHandler {
        fun bind(item: JobItem) {
            binding.jobItem=item
            binding.handler=this
        }

        override fun onFavoriteClick(jobItem: JobItem) {
            jobItem.isFavorite=!jobItem.isFavorite
            jobsViewModel.updateFavorite(jobItem)
            binding.jobItem=jobItem
        }

        override fun onItemClick(jobId:String,view : View) {
          val bundle= Bundle()
            bundle.putString("job_id",jobId)
            Navigation.findNavController(view).navigate(R.id.jobDetailsFragment,bundle)
        }


    }

}