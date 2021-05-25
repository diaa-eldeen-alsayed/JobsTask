package com.example.jobstask.model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jobs")
data class JobItem(
        val company: String,
        val company_logo: String?,
        val company_url: String?,
        val created_at: String,
        val description: String,
        val how_to_apply: String,
        @PrimaryKey val id: String,
        val location: String,
        val title: String,
        val type: String,
        val url: String,
        var isFavorite:Boolean
)
