package com.example.jobstask.local

import androidx.room.*
import com.example.jobstask.model.JobItem
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {
    @Query("SELECT * FROM Jobs")
    fun findAll(): Flow<List<JobItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(job: List<JobItem>)

    @Update
    fun update(job: JobItem)



}
