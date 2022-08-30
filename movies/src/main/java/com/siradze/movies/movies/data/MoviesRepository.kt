package com.siradze.movies.movies.data

import com.siradze.movies.util.Response

interface MoviesRepository {
    suspend fun getPopular(type : String, page : Int): Response<MoviesResponseData>
    suspend fun search(type : String, query : String, page : Int): Response<MoviesResponseData>
}