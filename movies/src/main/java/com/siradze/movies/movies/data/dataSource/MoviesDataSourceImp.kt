package com.siradze.movies.movies.data.dataSource

import com.siradze.movies.data.api.exceptions.NoConnectivityException
import com.siradze.movies.movies.data.api.MoviesApi
import com.siradze.movies.movies.data.api.MoviesResponse
import com.siradze.movies.movies.data.MoviesResponseData
import com.siradze.movies.util.Reason
import com.siradze.movies.util.Response

class MoviesDataSourceImp(private val moviesApi: MoviesApi) : MoviesDataSource {
    override suspend fun getPopular(type : String, page: Int): Response<MoviesResponseData> {
        return try {
            when (val response = moviesApi.getPopular(type, page)) {
                is MoviesResponse.Success -> {
                    val list = response.list.map { it.toMovie() }
                    Response.Success(MoviesResponseData(list, totalPages = response.totalPages))
                }
                is MoviesResponse.Error -> {
                    Response.Error(response.error.message)
                }
            }
        }catch (e : NoConnectivityException){
            e.printStackTrace()
            Response.Error(e.message, Reason.NoConnection)
        }catch (e : Exception){
            e.printStackTrace()
            Response.Error(e.message.toString())
        }
    }

    override suspend fun search(type : String, query : String, page : Int): Response<MoviesResponseData> {
        return try {
            when (val response = moviesApi.search(type, query, page)) {
                is MoviesResponse.Success -> {
                    val list = response.list.map { it.toMovie() }
                    Response.Success(MoviesResponseData(list, totalPages = response.totalPages))
                }
                is MoviesResponse.Error -> {
                    Response.Error(response.error.message)
                }
            }
        }catch (e : NoConnectivityException){
            e.printStackTrace()
            Response.Error(e.message, Reason.NoConnection)
        }catch (e : Exception){
            e.printStackTrace()
            Response.Error(e.message.toString())
        }
    }

}