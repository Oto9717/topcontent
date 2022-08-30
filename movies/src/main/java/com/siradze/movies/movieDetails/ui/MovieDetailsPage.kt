package com.siradze.movies.movieDetails.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.siradze.movies.MoviesType
import com.siradze.movies.R
import com.siradze.movies.movieDetails.ui.mvi.MovieDetailsEvent
import com.siradze.movies.movieDetails.ui.mvi.MovieDetailsState
import com.siradze.movies.composables.NoConnectionWidget
import com.siradze.movies.movieDetails.ui.composable.MovieDetailsCard
import com.siradze.movies.movieDetails.ui.composable.MoviePreviewCard


@Composable
fun MovieDetailsPage(navController : NavController, viewModel: MovieDetailsViewModel,
                     type : MoviesType, id:String) {
    LaunchedEffect(key1 = id){
        viewModel.event(MovieDetailsEvent.Load(type,id))
    }
    val state =  viewModel.state.collectAsState().value
    val list = viewModel.similarMoviesList

    if (state is MovieDetailsState.NoConnection){
        NoConnectionWidget(message = state.message, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)) {
            viewModel.event(MovieDetailsEvent.Load(type,id))
        }
    }else{
        Box(modifier = Modifier){
            val lazyColumnState = rememberLazyListState()
            LazyColumn(state = lazyColumnState, modifier = Modifier.fillMaxHeight()) {
                item {
                    val movieDetails = if(state is MovieDetailsState.Success) state.movieDetails else null
                    MovieDetailsCard(movieDetails, Modifier.padding(10.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "${stringResource(id = R.string.similar_movies)}:", Modifier.padding(horizontal = 10.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(modifier = Modifier.fillMaxWidth()){
                        if(list.isEmpty()){
                            repeat(5){
                                item{
                                    MoviePreviewCard(null, modifier = Modifier
                                        .fillMaxWidth()
                                        .height(230.dp)
                                        .padding(10.dp)){}
                                }
                            }
                        }else{
                            items(
                                items = list,
                                key = { movie -> movie.id }
                            ) { movie ->
                                MoviePreviewCard(movie, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(230.dp)
                                    .padding(10.dp))
                                {
                                    navController.navigate("movie_details/${it.id}")
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}