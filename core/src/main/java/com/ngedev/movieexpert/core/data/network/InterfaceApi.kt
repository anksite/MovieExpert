package com.ngedev.movieexpert.core.data.network

import com.ngedev.movieexpert.core.BuildConfig
import com.ngedev.movieexpert.core.data.network.response.ResponsePopular
import retrofit2.http.GET

interface InterfaceApi {
    @GET("3/movie/popular?api_key=${BuildConfig.KEY}")
    suspend fun popular(): ResponsePopular
}