package com.ngedev.movieexpert.core.data.network

import com.ngedev.movieexpert.core.data.network.response.ResponsePopular
import retrofit2.http.GET

interface InterfaceApi {
    @GET("3/movie/popular?api_key=0c4d2ec1c4152b9120a356051a53078e")
    suspend fun popular(): ResponsePopular
}