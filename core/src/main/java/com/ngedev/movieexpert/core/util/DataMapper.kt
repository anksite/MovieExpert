package com.ngedev.movieexpert.core.util

import com.ngedev.movieexpert.core.data.local.entity.MovieEntity
import com.ngedev.movieexpert.core.data.network.response.MovieResponse
import com.ngedev.movieexpert.core.domain.model.MovieData

object DataMapper {
    fun mapListResponseToListDomain(input: List<MovieResponse>): List<MovieData> =
        input.map {
            MovieData(
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                vote_average = it.vote_average
            )
        }

    fun mapListEntitiesToListDomain(input: List<MovieEntity>): List<MovieData> =
        input.map {
            MovieData(
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                vote_average = it.vote_average
            )
        }

    fun mapEntitiesToDomain(input: MovieEntity): MovieData =
        input.let {
            MovieData(
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                vote_average = it.vote_average
            )
        }

    fun mapDomainToEntities(input: MovieData): MovieEntity =
        input.let {
            MovieEntity(
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                vote_average = it.vote_average
            )
        }
}