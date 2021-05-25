package com.example.jobstask.local

import androidx.room.*
import com.example.jobstask.model.JobItem
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {
    @Query("SELECT * FROM Jobs")
    fun findAll(): Flow<List<JobItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addALL(job: List<JobItem>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(job: JobItem)

    @Query("SELECT * FROM Jobs WHERE id=:jobId")
    fun findJob(jobId:String):Boolean

    @Update
    fun update(job: JobItem):Int



}
