package com.siradze.movies.movieDetails.data.api

import com.siradze.movies.data.api.exceptions.ErrorResponseException
import com.siradze.movies.movieDetails.data.model.dto.MovieDetailsDto


sealed class MovieDetailsResponse {
    data class Success(val movieDetail : MovieDetailsDto) : MovieDetailsResponse()
    data class Error(val error : ErrorResponseException) : MovieDetailsResponse()
}
