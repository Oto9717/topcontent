package com.siradze.movies.moviesList.data.dataSource

import com.siradze.movies.data.api.exceptions.NoConnectivityException
import com.siradze.movies.moviesList.data.api.MoviesListApi
import com.siradze.movies.moviesList.data.api.MoviesListResponse

internal sealed class FakeMoviesListApi : MoviesListApi {
    object Success : FakeMoviesListApi() {
        override suspend fun getPopular(type: String, page: Int): MoviesListResponse {
            return MoviesListResponse.Success(emptyList(), 1)
        }

        override suspend fun search(type: String, query: String, page: Int): MoviesListResponse {
            return MoviesListResponse.Success(emptyList(), 1)
        }

    }
    object ErrorNoConnection : FakeMoviesListApi() {
        override suspend fun getPopular(type: String, page: Int): MoviesListResponse {
            throw NoConnectivityException()
        }

        override suspend fun search(type: String, query: String, page: Int): MoviesListResponse {
            throw NoConnectivityException()
        }

    }

    object Error : FakeMoviesListApi() {
        override suspend fun getPopular(type: String, page: Int): MoviesListResponse {
            throw Exception()
        }

        override suspend fun search(type: String, query: String, page: Int): MoviesListResponse {
            throw Exception()
        }

    }
}