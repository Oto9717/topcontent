package com.siradze.movies.movieDetails.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siradze.movies.MoviesType
import com.siradze.movies.data.model.Movie
import com.siradze.movies.movieDetails.data.MovieDetailsRepository
import com.siradze.movies.movieDetails.ui.mvi.MovieDetailsEvent
import com.siradze.movies.movieDetails.ui.mvi.MovieDetailsState
import com.siradze.movies.util.Reason
import com.siradze.movies.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieDetailsRepository)  : ViewModel() {

    private var type : MoviesType = MoviesType.Movies

    private val _state  : MutableStateFlow<MovieDetailsState> = MutableStateFlow(MovieDetailsState.Loading)
    val state = _state.asStateFlow()

    val similarMoviesList : SnapshotStateList<Movie> = mutableStateListOf()
    private var page : Int = 1
    private var id : String = ""


    fun event(event : MovieDetailsEvent) = viewModelScope.launch {
        when(event){
            is MovieDetailsEvent.Load -> {
                id = event.id
                type = event.type
                load()
                loadSimilarMovies()
            }
        }
    }
    private suspend fun load() = withContext(Dispatchers.IO){
        when(val response = repository.getMovieDetails(type.typeName, id)){
            is Response.Success -> {
                _state.emit(MovieDetailsState.Success(response.data))
            }
            is Response.Error -> {
                when(response.reason){
                    Reason.NoConnection -> {
                        _state.emit(MovieDetailsState.NoConnection("Check your internet connection"))
                    }
                    Reason.Other -> {
                        _state.emit(MovieDetailsState.NoConnection(message = response.message))
                    }
                }
            }
        }
    }

    private suspend fun loadSimilarMovies() = withContext(Dispatchers.IO){
        when(val response = repository.getSimilarMovies(type.typeName, id, page)){
            is Response.Success -> {
                //Check if new movies list has same move that we already have and filter out
                similarMoviesList.addAll(response.data.filter {
                    !similarMoviesList.map { it.id }.contains(it.id)
                })
            }
            is Response.Error -> {
                when(response.reason){
                    Reason.NoConnection -> {
                        _state.emit(MovieDetailsState.NoConnection("Check your internet connection"))
                    }
                    Reason.Other -> {
                        _state.emit(MovieDetailsState.NoConnection(message = response.message))
                    }
                }
            }
        }
    }
}