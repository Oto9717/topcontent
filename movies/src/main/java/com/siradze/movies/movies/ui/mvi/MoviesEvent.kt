package com.siradze.movies.movies.ui.mvi

import com.siradze.movies.MoviesType


sealed class MoviesEvent{

    data class InitLoad(val type : MoviesType) : MoviesEvent()
    object LoadMore : MoviesEvent()
    data class OnSearchQueryChange(val query : String, val instant: Boolean = false) : MoviesEvent()
}