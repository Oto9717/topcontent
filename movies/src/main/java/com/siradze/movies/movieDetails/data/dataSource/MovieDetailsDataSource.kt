package com.siradze.movies.movieDetails.data.dataSource

import com.siradze.movies.data.model.Movie
import com.siradze.movies.movieDetails.data.model.MovieDetails
import com.siradze.movies.util.Response

interface MovieDetailsDataSource {
    suspend fun getMovieDetails(type : String, id : String) : Response<MovieDetails>
    suspend fun getSimilarMovies(type : String, id : String, page: Int): Response<List<Movie>>
}