package com.siradze.movies.moviesList.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.siradze.movies.MoviesType
import com.siradze.movies.moviesList.ui.mvi.MoviesListState
import com.siradze.movies.moviesList.ui.mvi.MoviesListEvent
import com.siradze.movies.composables.NoConnectionWidget
import com.siradze.movies.moviesList.ui.composables.SearchBar
import com.siradze.movies.moviesList.ui.composables.list.MovieCard
import com.siradze.movies.moviesList.ui.composables.list.OnBottomReached

@Composable
internal fun MoviesListPage(navController : NavHostController, viewModel: MoviesListViewModel, type : MoviesType) {

   LaunchedEffect(key1 = type){
      viewModel.event(MoviesListEvent.InitLoad(type))
   }

   val listState = viewModel.listState.collectAsState().value
   val listData = viewModel.listData
   val searchState = viewModel.searchState.collectAsState().value

   val lazyColumnState = rememberLazyListState()

   BackHandler(enabled = searchState.value.isNotEmpty()) {
      viewModel.event(MoviesListEvent.OnSearchQueryChange("", true))
   }


   LazyColumn(state = lazyColumnState) {
      item {
         SearchBar(searchState, Modifier.fillMaxWidth().padding(10.dp)){ query, instant ->
            viewModel.event(MoviesListEvent.OnSearchQueryChange(query, instant))
         }
      }

      when(listState) {
         MoviesListState.Loading -> {
            repeat(5){
               item{
                  MovieCard(movie = null, modifier = Modifier
                     .fillMaxWidth()
                     .height(230.dp)
                     .padding(10.dp)
                     .alpha(0.5f)){}
               }
            }
         }
         else -> {
            items(
               items = listData,
               key = { movie -> movie.id }
            ) { movie ->
               MovieCard(movie, modifier = Modifier
                  .fillMaxWidth()
                  .height(230.dp)
                  .padding(10.dp))
               {
                  navController.navigate("movie_details/${it.id}")
               }
            }
            item {
               val alpha = if (listState is MoviesListState.Success && listState.isLoadingMore) 1f else 0f
               Box(contentAlignment = Alignment.BottomCenter) {
                  LinearProgressIndicator(
                     Modifier
                        .alpha(alpha)
                        .fillMaxWidth())
               }
            }
            if(listState is MoviesListState.NoConnection){
               item {
                  NoConnectionWidget(listState.message,
                     Modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                     viewModel.event(MoviesListEvent.LoadMore)
                  }
               }
            }
         }
      }

   }
   if(listState is MoviesListState.Success && listState.canLoadMore){
      lazyColumnState.OnBottomReached{
         viewModel.event(MoviesListEvent.LoadMore)
      }
   }

}