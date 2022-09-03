package com.siradze.movies.moviesList.data.api

import com.siradze.movies.data.api.exceptions.ErrorResponseException
import com.siradze.movies.data.model.dto.MovieDto

internal sealed class MoviesListResponse {
    data class Success(val list : List<MovieDto>, val totalPages : Int) : MoviesListResponse()
    data class Error(val error : ErrorResponseException) : MoviesListResponse()
}
