package com.ngedev.movieexpert.core.domain.usecase

import com.ngedev.movieexpert.core.domain.model.MovieData
import com.ngedev.movieexpert.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override suspend fun getPopularMovie() = movieRepository.getPopularMovie()

    override suspend fun selectListFavorite(): Flow<List<MovieData>> =
        movieRepository.selectListFavorite()

    override suspend fun selectMovieFavorite(title: String): Flow<MovieData?> =
        movieRepository.selectMovieFavorite(title)

    override suspend fun insertFavorite(movieData: MovieData) =
        movieRepository.insertFavorite(movieData)

    override suspend fun deleteFavorite(movieData: MovieData) =
        movieRepository.deleteFavorite(movieData)
}