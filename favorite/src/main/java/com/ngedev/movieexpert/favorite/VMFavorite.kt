package com.ngedev.movieexpert.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngedev.movieexpert.core.domain.model.MovieData
import com.ngedev.movieexpert.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VMFavorite(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val mListFavorite = MutableLiveData<List<MovieData>>()
    val listFavorite: LiveData<List<MovieData>> = mListFavorite

    init {
        viewModelScope.launch {
            movieUseCase.selectListFavorite().collect {
                mListFavorite.postValue(it)
            }
        }
    }
}