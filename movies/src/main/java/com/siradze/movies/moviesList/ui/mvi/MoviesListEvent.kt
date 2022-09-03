package com.siradze.movies.moviesList.ui.mvi

import com.siradze.movies.MoviesType


internal sealed class MoviesListEvent{

    data class InitLoad(val type : MoviesType) : MoviesListEvent()
    object LoadMore : MoviesListEvent()
    data class OnSearchQueryChange(val query : String, val instant: Boolean = false) : MoviesListEvent()
}