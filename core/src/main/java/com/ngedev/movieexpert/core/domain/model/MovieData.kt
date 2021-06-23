package com.ngedev.movieexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String?,
    val vote_average: String
) : Parcelable

