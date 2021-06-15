package com.ngedev.movieexpert.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ngedev.movieexpert.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoFavorite {
    @Insert
    suspend fun insertFavorite(data: MovieEntity)

    @Delete
    suspend fun deleteFavorite(data: MovieEntity)

    @Query("SELECT * FROM MovieEntity")
    fun selectListFavorite(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE title = :title")
    fun selectMovieFavorite(title: String): Flow<MovieEntity?>
}