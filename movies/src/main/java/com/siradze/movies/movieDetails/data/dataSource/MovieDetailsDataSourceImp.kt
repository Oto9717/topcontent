package com.siradze.movies.movieDetails.data.dataSource
import com.siradze.movies.data.api.exceptions.NoConnectivityException
import com.siradze.movies.domain.model.Movie
import com.siradze.movies.movieDetails.data.api.MovieDetailsApi
import com.siradze.movies.movieDetails.data.api.MovieDetailsResponse
import com.siradze.movies.movieDetails.data.api.SimilarMoviesResponse
import com.siradze.movies.movieDetails.data.model.MovieDetails
import com.siradze.movies.util.Reason
import com.siradze.movies.util.Response

internal class MovieDetailsDataSourceImp(private val api : MovieDetailsApi) : MovieDetailsDataSource {
    override suspend fun getMovieDetails(type : String, id : String): Response<MovieDetails> {
        val response = api.getDetails(type, id)
        return responseHandler {
            when (response) {
                is MovieDetailsResponse.Success -> {
                    Response.Success(response.movieDetail.toMovieDetail())
                }
                is MovieDetailsResponse.Error -> {
                    Response.Error(response.error.message)
                }
            }
        }
    }

    override suspend fun getSimilarMovies(type : String, id : String, page: Int): Response<List<Movie>> {

        val response = api.getSimilarMovies(type, id, page)

        return responseHandler{
            when (response) {
                is SimilarMoviesResponse.Success -> {
                    val list = response.list.map { it.toMovie() }
                    Response.Success(list)
                }
                is SimilarMoviesResponse.Error -> {
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
