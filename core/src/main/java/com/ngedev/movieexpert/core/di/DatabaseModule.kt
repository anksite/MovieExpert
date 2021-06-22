package com.ngedev.movieexpert.core.di

import android.content.Context
import androidx.room.Room
import com.ngedev.movieexpert.core.data.local.room.AppDatabase
import com.ngedev.movieexpert.core.data.local.room.DaoFavorite
import com.ngedev.movieexpert.core.util.Cons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase: ByteArray = SQLiteDatabase.getBytes("ngedev".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Cons.DATABASE_NAME)
            .fallbackToDestructiveMigration().openHelperFactory(factory).build()

    @Provides
    fun provideDaoFavorite(database: AppDatabase): DaoFavorite = database.daoFavorite()
}