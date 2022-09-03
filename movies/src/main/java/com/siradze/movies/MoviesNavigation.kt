package com.siradze.movies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.siradze.movies.movieDetails.ui.MovieDetailsPage
import com.siradze.movies.movieDetails.ui.MovieDetailsViewModel
import com.siradze.movies.moviesList.ui.MoviesListPage
import com.siradze.movies.moviesList.ui.MoviesListViewModel

@Composable
fun MoviesNavigation(type : MoviesType) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movies") {
        composable("movies") {
            val viewModel = hiltViewModel<MoviesListViewModel>()
            MoviesListPage(navController,viewModel, type)
        }
        composable("movie_details/{id}"){
            val id = it.arguments?.getString("id")
            if(id == null){
                navController.popBackStack()
                return@composable
            }
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            MovieDetailsPage(navController, viewModel, type, id)
        }

    }
}

enum class MoviesType(val typeName : String) {
    TV("tv"),
    Movies("movie"),
}