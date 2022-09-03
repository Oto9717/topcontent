package com.siradze.movies.moviesList.domain.repository

import com.siradze.movies.moviesList.domain.model.MoviesResponseData
import com.siradze.movies.util.Response

internal interface MoviesListRepository {
    suspend fun getPopular(type : String, page : Int): Response<MoviesResponseData>
    suspend fun search(type : String, query : String, page : Int): Response<MoviesResponseData>
}