package com.ngedev.movieexpert.core.data.network.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("vote_average") val vote_average: String
)

