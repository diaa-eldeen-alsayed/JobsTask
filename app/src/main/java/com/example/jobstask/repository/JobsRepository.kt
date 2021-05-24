package com.example.jobstask.repository

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.jobstask.di.apiModule
import com.example.jobstask.model.Job
import com.example.jobstask.model.JobItem
import com.example.jobstask.model.Result
import com.example.jobstask.remote.Webservice
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class JobsRepository (private val api: Webservice,
                      private val context: Context) {


    suspend fun getAllJobs() : Flow<Result<Job>?> {

        return flow {
            emit(Result.loading(null))
                       val result=api.getJobs()
            if(result.isSuccessful && result.body()!=null){
                emit(Result.success(result.body()))
            }
            else{
            emit(Result.error("error"))
            }



        }.flowOn(Dispatchers.IO)



    }




}