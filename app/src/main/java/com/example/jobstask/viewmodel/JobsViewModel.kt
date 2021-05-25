package com.example.jobstask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobstask.model.Job
import com.example.jobstask.model.JobItem
import com.example.jobstask.model.Result
import com.example.jobstask.repository.JobsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class JobsViewModel(private val repository : JobsRepository):ViewModel() {
    private var _jobList = MutableLiveData<Result<List<JobItem>>>()
    
    
    fun getAllJobs():MutableLiveData<Result<List<JobItem>>> {
        viewModelScope.launch {
            repository.getAllJobs().collect {
                 _jobList.value=it
             }
        }
        return _jobList
    }

    fun updateFavorite(jobItem:JobItem){
        viewModelScope.launch {
        repository.updateFavorite(jobItem)
        }

    }

}