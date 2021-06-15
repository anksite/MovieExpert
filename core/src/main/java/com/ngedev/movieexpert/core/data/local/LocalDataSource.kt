package com.ngedev.movieexpert.core.data.local

import com.ngedev.movieexpert.core.data.local.entity.MovieEntity
import com.ngedev.movieexpert.core.data.local.room.DaoFavorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val daoFavorite: DaoFavorite) {

    fun selectListFavorite(): Flow<List<MovieEntity>> = daoFavorite.selectListFavorite()

    fun selectMovieFavorite(title: String): Flow<MovieEntity?> =
        daoFavorite.selectMovieFavorite(title)

    suspend fun insertFavorite(movieEntity: MovieEntity) = daoFavorite.insertFavorite(movieEntity)

    suspend fun deleteFavorite(movieEntity: MovieEntity) = daoFavorite.deleteFavorite(movieEntity)
}