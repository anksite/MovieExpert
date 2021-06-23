package com.ngedev.movieexpert.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    @NonNull
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String?,
    val vote_average: String
)
