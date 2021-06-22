package com.ngedev.movieexpert.core.data.network.response

import com.google.gson.annotations.SerializedName

data class ResponsePopular(
    @SerializedName("results") val results: List<MovieResponse>
)

