package com.siradze.movies.movieDetails.data.api

import com.siradze.movies.data.api.exceptions.ErrorResponseException
import com.siradze.movies.data.model.dto.MovieDto

sealed class SimilarMoviesResponse {
    data class Success(val list : List<MovieDto>) : SimilarMoviesResponse()
    data class Error(val error : ErrorResponseException) : SimilarMoviesResponse()
}