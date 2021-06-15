package com.ngedev.movieexpert.core.data

import com.ngedev.movieexpert.core.data.local.LocalDataSource
import com.ngedev.movieexpert.core.data.network.RemoteDataSource
import com.ngedev.movieexpert.core.domain.model.MovieData
import com.ngedev.movieexpert.core.domain.repository.IMovieRepository
import com.ngedev.movieexpert.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

    override suspend fun getPopularMovie(): Flow<List<MovieData>> =
        remoteDataSource.getPopular()
            .map { DataMapper.mapListResponseToListDomain(it) }

    override suspend fun selectListFavorite(): Flow<List<MovieData>> =
        localDataSource.selectListFavorite()
            .map { DataMapper.mapListEntitiesToListDomain(it) }


    override suspend fun selectMovieFavorite(title: String): Flow<MovieData?> =
        localDataSource.selectMovieFavorite(title)
            .map { it?.let { DataMapper.mapEntitiesToDomain(it) } }


    override suspend fun insertFavorite(movieData: MovieData) =
        localDataSource.insertFavorite(DataMapper.mapDomainToEntities(movieData))

    override suspend fun deleteFavorite(movieData: MovieData) =
        localDataSource.deleteFavorite(DataMapper.mapDomainToEntities(movieData))

}