package com.siradze.movies.moviesList.data.dataSource

import com.siradze.movies.data.api.exceptions.NoConnectivityException
import com.siradze.movies.moviesList.data.api.MoviesListApi
import com.siradze.movies.moviesList.data.api.MoviesListResponse
import com.siradze.movies.moviesList.domain.model.MoviesResponseData
import com.siradze.movies.util.Reason
import com.siradze.movies.util.Response

internal class MoviesListDataSourceImp(private val moviesListApi: MoviesListApi) : MoviesListDataSource {
    override suspend fun getPopular(type : String, page: Int): Response<MoviesResponseData> {
        val response = moviesListApi.getPopular(type, page)
        return responseHandler {
            when (response) {
                is MoviesListResponse.Success -> {
                    val list = response.list.map { it.toMovie() }
                    Response.Success(MoviesResponseData(list, totalPages = response.totalPages))
                }
                is MoviesListResponse.Error -> {
                    Response.Error(response.error.message)
                }
            }
        }
    }

    override suspend fun search(type : String, query : String, page : Int): Response<MoviesResponseData> {
        val response = moviesListApi.search(type, query, page)
        return responseHandler {
            when (response) {
                is MoviesListResponse.Success -> {
                    val list = response.list.map { it.toMovie() }
                    Response.Success(MoviesResponseData(list, totalPages = response.totalPages))
                }
                is MoviesListResponse.Error -> {
                    Response.Error(response.error.message)
                }
            }
        }
    }


    private fun <T> responseHandler(function : () -> Response<T>) : Response<T>{
        return try {
            function()
        }catch (e : NoConnectivityException){
            Response.Error(e.message, Reason.NoConnection)
        }catch (e : Exception){
            Response.Error(e.message.toString())
        }
    }

}