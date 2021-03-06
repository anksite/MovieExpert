package com.ngedev.movieexpert.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngedev.movieexpert.core.domain.model.MovieData
import com.ngedev.movieexpert.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMMovieDetail @Inject constructor(val movieUseCase: MovieUseCase) : ViewModel() {
    private val mIsFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = mIsFavorite

    fun checkMovieFavorite(title: String) {
        viewModelScope.launch {
            movieUseCase.selectMovieFavorite(title).collect {
                mIsFavorite.postValue(it != null)
            }
        }
    }

    fun insertFavorite(movieData: MovieData) {
        viewModelScope.launch {
            movieUseCase.insertFavorite(movieData)
        }
    }

    fun deleteFavorite(movieData: MovieData) {
        viewModelScope.launch {
            movieUseCase.deleteFavorite(movieData)
        }

    }
}