package com.siradze.movies.movies.ui.mvi

sealed class MoviesListState {
    object Loading : MoviesListState()
    data class Success(
        val isLoadingMore : Boolean,
        val canLoadMore : Boolean = true
    ) : MoviesListState()
    data class NoConnection(val message : String) : MoviesListState()
}
data class SearchState(val value : String = "")