package com.example.jobstask.di

import android.app.Application
import androidx.room.Room
import com.example.jobstask.local.JobsDao
import com.example.jobstask.local.JobsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): JobsDatabase {
        return Room.databaseBuilder(application, JobsDatabase::class.java, "jobs")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideJobDao(database:JobsDatabase): JobsDao{
        return  database.JobsDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideJobDao(get()) }


}