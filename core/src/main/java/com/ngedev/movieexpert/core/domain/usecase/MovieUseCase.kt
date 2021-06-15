package com.ngedev.movieexpert.core.domain.usecase

import com.ngedev.movieexpert.core.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getPopularMovie(): Flow<List<MovieData>>

    suspend fun selectListFavorite(): Flow<List<MovieData>>

    suspend fun selectMovieFavorite(title: String): Flow<MovieData?>

    suspend fun insertFavorite(movieData: MovieData)

    suspend fun deleteFavorite(movieData: MovieData)
}