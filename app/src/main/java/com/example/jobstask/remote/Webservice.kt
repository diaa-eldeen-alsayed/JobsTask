package com.example.jobstask.remote

import com.example.jobstask.model.Job
import retrofit2.Response
import retrofit2.http.GET

interface Webservice {

    @GET("positions.json?description=api")
    suspend fun getJobs(): Response<Job>


}