package com.siradze.movies.movieDetails.data.dataSource

import com.siradze.movies.domain.model.Movie
import com.siradze.movies.movieDetails.domain.model.MovieDetails
import com.siradze.movies.util.Response

internal interface MovieDetailsDataSource {
    suspend fun getMovieDetails(type : String, id : String) : Response<MovieDetails>
    suspend fun getSimilarMovies(type : String, id : String, page: Int): Response<List<Movie>>
}