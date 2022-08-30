package com.siradze.movies.movies.data.dataSource

import com.siradze.movies.movies.data.MoviesResponseData
import com.siradze.movies.util.Response

interface MoviesDataSource {
    suspend fun getPopular(type : String, page : Int) : Response<MoviesResponseData>
    suspend fun search(type : String, query : String, page : Int): Response<MoviesResponseData>
}