package com.siradze.movies.movieDetails.data.dataSource

import com.siradze.movies.data.api.ApiStrings
import com.siradze.movies.data.api.exceptions.NoConnectivityException
import com.siradze.movies.data.model.Movie
import com.siradze.movies.movieDetails.data.api.MovieDetailsApi
import com.siradze.movies.movieDetails.data.api.MovieDetailsResponse
import com.siradze.movies.movieDetails.data.api.SimilarMoviesResponse
import com.siradze.movies.movieDetails.data.model.MovieDetails
import com.siradze.movies.movies.data.api.MoviesResponse
import com.siradze.movies.util.Reason
import com.siradze.movies.util.Response

class MovieDetailsDataSourceImp(private val api : MovieDetailsApi) : MovieDetailsDataSource {
    override suspend fun getMovieDetails(type : String, id : String): Response<MovieDetails> {

        return try {
            when (val response = api.getDetails(type, id.toString())) {
                is MovieDetailsResponse.Success -> {
                    Response.Success(response.movieDetail.toMovieDetail())
                }
                is MovieDetailsResponse.Error -> {
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

    override suspend fun getSimilarMovies(type : String, id : String, page: Int): Response<List<Movie>> {
        return try {
            when (val response = api.getSimilarMovies(type, id, page)) {
                is SimilarMoviesResponse.Success -> {
                    val list = response.list.map { it.toMovie() }
                    Response.Success(list)
                }
                is SimilarMoviesResponse.Error -> {
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