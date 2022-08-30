package com.siradze.movies.movies.data.api

import com.siradze.movies.data.api.exceptions.ErrorResponseException
import com.siradze.movies.data.model.dto.MovieDto

sealed class MoviesResponse {
    data class Success(val list : List<MovieDto>, val totalPages : Int) : MoviesResponse()
    data class Error(val error : ErrorResponseException) : MoviesResponse()
}
