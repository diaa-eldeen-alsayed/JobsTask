package com.example.jobstask.repository

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.jobstask.di.apiModule
import com.example.jobstask.helpers.NetworkUtils
import com.example.jobstask.local.JobsDao
import com.example.jobstask.model.Job
import com.example.jobstask.model.JobItem
import com.example.jobstask.model.Result
import com.example.jobstask.remote.Webservice
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


class JobsRepository (private val api: Webservice,
                      private val context: Context,
                      private val dao: JobsDao) {


    suspend fun getAllJobs() : Flow<Result<List<JobItem>>> {
        if (NetworkUtils.isOnline(context)) {
            return flow {
                emit(Result.loading(null))
                val result = api.getJobs()
                if (result.isSuccessful && result.body() != null) {
                    result.body()?.let {
                        withContext(Dispatchers.IO) {
                            for (item in it){
                                dao.add(item) }

                        }
                            emit(Result.success(dao.findAll().first()))
                        }
                    }

                 else {
                    emit(Result.error("error"))
                }
            }.flowOn(Dispatchers.IO)
        }
        else{
           return getJobsDataFromCache()
        }

    }
    private suspend fun getJobsDataFromCache(): Flow<Result<List<JobItem>>> {
        return flow{
            emit(Result.loading(null))
            val result = dao.findAll()
            if(result.first().isNotEmpty()){
                emit(Result.success(result.first()))
            }
            else{
                emit(Result.error("not found Data "))
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun updateFavorite(jobItem:JobItem){
        withContext(Dispatchers.IO) {
            val isUpdate:Int= dao.update(jobItem)

        }

    }







}