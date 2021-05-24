package com.example.jobstask.di

import com.example.jobstask.remote.Webservice
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideJobsApi(retrofit: Retrofit): Webservice {
        return retrofit.create(Webservice::class.java)
    }
    single { provideJobsApi(get()) }

}