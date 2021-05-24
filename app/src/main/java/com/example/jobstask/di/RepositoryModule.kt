package com.example.jobstask.di

import android.content.Context
import com.example.jobstask.remote.Webservice
import com.example.jobstask.repository.JobsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideJobsRepository(api: Webservice, context: Context): JobsRepository {
        return JobsRepository(api, context)
    }
    single { provideJobsRepository(get(), androidContext()) }


}