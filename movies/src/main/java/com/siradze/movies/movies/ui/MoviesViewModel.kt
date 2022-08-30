package com.siradze.movies.movies.ui
import android.app.Application
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siradze.movies.MoviesType
import com.siradze.movies.R
import com.siradze.movies.movies.data.MoviesRepository
import com.siradze.movies.data.model.Movie
import com.siradze.movies.movies.data.MoviesResponseData
import com.siradze.movies.movies.ui.mvi.MoviesListState
import com.siradze.movies.movies.ui.mvi.MoviesEvent
import com.siradze.movies.movies.ui.mvi.SearchState
import com.siradze.movies.util.Reason
import com.siradze.movies.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(application: Application,
    private val repository: MoviesRepository,
    )  : AndroidViewModel(application) {

    companion object{
        const val MAX_PAGE_NUMBER = 500
    }

    private var type : MoviesType = MoviesType.Movies

    private val _listState : MutableStateFlow<MoviesListState> = MutableStateFlow(MoviesListState.Loading)
    val listState = _listState.asStateFlow()
    val listData : SnapshotStateList<Movie> = mutableStateListOf()
    private var page : Int = 1
    private var totalPages = 1


    private val _searchState : MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()
    private var searchJob : Job? = null
    private var searchQuery = ""

    fun event(event : MoviesEvent) = viewModelScope.launch {
        when (event){
            is MoviesEvent.InitLoad -> {
                type = event.type
                reload()
            }

            MoviesEvent.LoadMore -> {
                loadMore()
            }
            is MoviesEvent.OnSearchQueryChange -> {
                _searchState.emit(SearchState(event.query))
                page = 1
                search(event.query, event.instant)
            }
        }
    }

    private suspend fun reload(){
        if(listData.isNotEmpty()){return}
        _listState.emit(MoviesListState.Loading)
        page = 1
        load()
    }
    private suspend fun loadMore(){
        _listState.emit(MoviesListState.Success(true))
        page++
        if(page >= MAX_PAGE_NUMBER){
            _listState.emit(MoviesListState.Success(
                isLoadingMore = false,
                canLoadMore = false
            ))
            return
        }
        if(searchQuery.trim().isEmpty()){
            load()
        }else{
            loadSearch()
        }

    }



    private suspend fun load() = withContext(Dispatchers.IO){
        val response = repository.getPopular(type.typeName, page)
        handleResponse(response)
    }

    // handle search query change
    private suspend fun search(query : String, instant : Boolean){
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            if(!instant){
                delay(1000)
            }

            if(query.trim().isEmpty()){
                listData.clear()
                reload()
                return@launch
            }
            searchQuery = query
            listData.clear()
            _listState.emit(MoviesListState.Loading)
            loadSearch()
        }
    }

    //load more pages of search query
    private suspend fun loadSearch() = withContext(Dispatchers.IO){
        val response = repository.search(type.typeName, searchQuery, page)
        yield()
        handleResponse(response)
    }

    private suspend fun handleResponse(response : Response<MoviesResponseData>){
        when (response) {
            is Response.Success -> {
                totalPages = response.data.totalPages
                //Check if new movies list has same move that we already have and filter out
                listData.addAll(response.data.list.filter {
                    !listData.map { it.id }.contains(it.id)
                })
                _listState.emit(MoviesListState.Success(false, totalPages - page > 0))
            }
            is Response.Error -> {
                when(response.reason){
                    Reason.NoConnection -> {
                        _listState.emit(MoviesListState.NoConnection(string(R.string.no_connection)))
                    }
                    Reason.Other -> {
                        _listState.emit(MoviesListState.NoConnection(message = response.message))
                    }
                }
            }
        }
    }


    private fun string(@StringRes id : Int): String {
        return getApplication<Application>().resources.getString(id)
    }

}

