package com.siradze.movies.moviesList.data

import com.siradze.movies.moviesList.data.dataSource.MoviesListDataSource
import com.siradze.movies.moviesList.domain.model.MoviesResponseData
import com.siradze.movies.moviesList.domain.repository.MoviesListRepository
import com.siradze.movies.util.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MoviesListRepositoryImp (private val dataSource: MoviesListDataSource) : MoviesListRepository {
    override suspend fun getPopular(type : String, page: Int):
            Response<MoviesResponseData> = withContext(Dispatchers.IO) {
        dataSource.getPopular(type, page)
    }

    override suspend fun search(type : String, query : String, page : Int):
            Response<MoviesResponseData> = withContext(Dispatchers.IO) {
        dataSource.search(type,  query, page)
    }
}