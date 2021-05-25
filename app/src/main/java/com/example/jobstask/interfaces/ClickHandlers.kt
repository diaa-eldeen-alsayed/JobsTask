package com.example.jobstask.interfaces

import android.view.View
import com.example.jobstask.model.JobItem

class ClickHandlers {
    public interface  FirstScreenHandler{
        fun onStartClick()
    }
    public interface JobListHandler{
        fun onFavoriteClick(jobItem: JobItem)
        fun onItemClick(jobId:String,view :View)
    }
}