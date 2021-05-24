package com.example.jobstask.App

import android.app.Application
import com.example.jobstask.di.apiModule
import com.example.jobstask.di.databaseModule
import com.example.jobstask.di.repositoryModule
import com.example.jobstask.di.viewModelModule
import networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class JobsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@JobsApplication)
            modules(
                apiModule,
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}