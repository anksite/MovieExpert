package com.ngedev.movieexpert.core.data.network

import com.ngedev.movieexpert.core.data.network.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val interfaceApi: InterfaceApi) {

    suspend fun getPopular(): Flow<List<MovieResponse>> = flow {
        val response = interfaceApi.popular()
        emit(response.results)
    }.flowOn(Dispatchers.IO)
}