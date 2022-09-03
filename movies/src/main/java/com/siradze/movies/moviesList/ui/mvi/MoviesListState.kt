package com.siradze.movies.moviesList.ui.mvi

internal sealed class MoviesListState {
    object Loading : MoviesListState()
    data class Success(
        val isLoadingMore : Boolean,
        val canLoadMore : Boolean = true
    ) : MoviesListState()
    data class NoConnection(val message : String) : MoviesListState()
}
internal data class SearchState(val value : String = "")