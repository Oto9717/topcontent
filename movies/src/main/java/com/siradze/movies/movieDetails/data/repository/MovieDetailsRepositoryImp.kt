package com.siradze.movies.movieDetails.data.repository

import com.siradze.movies.domain.model.Movie
import com.siradze.movies.movieDetails.data.dataSource.MovieDetailsDataSource
import com.siradze.movies.movieDetails.data.model.MovieDetails
import com.siradze.movies.movieDetails.domain.repository.MovieDetailsRepository
import com.siradze.movies.util.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MovieDetailsRepositoryImp(private val dataSource: MovieDetailsDataSource) :
    MovieDetailsRepository {
    override suspend fun getMovieDetails(type : String, id : String):
            Response<MovieDetails> = withContext(Dispatchers.IO){
        dataSource.getMovieDetails(type, id)
    }
    override suspend fun getSimilarMovies(type : String, id : String, page: Int):
            Response<List<Movie>> = withContext(Dispatchers.IO) {
        dataSource.getSimilarMovies(type, id, page)
    }
}