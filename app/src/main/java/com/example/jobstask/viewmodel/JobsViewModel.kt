package com.example.jobstask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobstask.model.Job
import com.example.jobstask.model.Result
import com.example.jobstask.repository.JobsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class JobsViewModel(private val repository : JobsRepository):ViewModel() {
    private val _jobList = MutableLiveData<Result<Job>>()
    val jobList=_jobList;
    init {
        getAllJobs()
    }

    fun getAllJobs() {

        viewModelScope.launch {
            repository.getAllJobs().collect {
                 _jobList.value=it
             }
        }
    }

}