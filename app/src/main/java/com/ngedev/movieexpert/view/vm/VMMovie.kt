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
class VMMovie @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    private val mListMovie = MutableLiveData<List<MovieData>>()
    val listMovie: LiveData<List<MovieData>> = mListMovie

    init {
        viewModelScope.launch {
            movieUseCase.getPopularMovie().collect {
                mListMovie.postValue(it)
            }
        }
    }
}