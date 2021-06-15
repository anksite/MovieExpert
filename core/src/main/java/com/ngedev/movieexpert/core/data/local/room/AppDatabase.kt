package com.ngedev.movieexpert.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ngedev.movieexpert.core.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoFavorite(): DaoFavorite
}