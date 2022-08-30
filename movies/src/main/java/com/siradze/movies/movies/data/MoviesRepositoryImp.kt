package com.siradze.movies.movies.data

import com.siradze.movies.movies.data.dataSource.MoviesDataSource
import com.siradze.movies.util.Response

class MoviesRepositoryImp (private val dataSource: MoviesDataSource) : MoviesRepository {
    override suspend fun getPopular(type : String, page: Int): Response<MoviesResponseData> {
        return dataSource.getPopular(type, page)
    }

    override suspend fun search(type : String, query : String, page : Int):  Response<MoviesResponseData> {
        return dataSource.search(type,  query, page)
    }
}