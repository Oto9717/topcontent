package com.siradze.movies.movieDetails.ui.mvi

import com.siradze.movies.movieDetails.data.model.MovieDetails

internal sealed class MovieDetailsState {
    object Loading : MovieDetailsState()
    data class Success(
      val movieDetails : MovieDetails
    ) : MovieDetailsState()
    data class NoConnection(val message : String) : MovieDetailsState()
}