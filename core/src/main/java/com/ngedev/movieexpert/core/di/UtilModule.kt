package com.ngedev.movieexpert.core.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.ngedev.movieexpert.core.ui.RecyclerAdapterMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context): RequestManager = Glide.with(context)

    @Singleton
    @Provides
    fun provideRecyclerAdapterMovie(): RecyclerAdapterMovie = RecyclerAdapterMovie()
}