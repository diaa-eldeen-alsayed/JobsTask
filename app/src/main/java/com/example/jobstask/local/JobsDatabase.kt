package com.example.jobstask.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jobstask.model.JobItem

@Database(
    entities = [JobItem::class],
    version = 4, exportSchema = false
)
abstract class JobsDatabase : RoomDatabase(){
    abstract val JobsDao: JobsDao
}