package com.siradze.movies.movieDetails.data

import com.siradze.movies.data.model.Movie
import com.siradze.movies.movieDetails.data.dataSource.MovieDetailsDataSource
import com.siradze.movies.movieDetails.data.model.MovieDetails
import com.siradze.movies.util.Response

class MovieDetailsRepositoryImp(private val dataSource: MovieDetailsDataSource) : MovieDetailsRepository {
    override suspend fun getMovieDetails(type : String, id : String): Response<MovieDetails> {
        return dataSource.getMovieDetails(type, id)
    }
    override suspend fun getSimilarMovies(type : String, id : String, page: Int): Response<List<Movie>> {
        return dataSource.getSimilarMovies(type, id, page)
    }
}