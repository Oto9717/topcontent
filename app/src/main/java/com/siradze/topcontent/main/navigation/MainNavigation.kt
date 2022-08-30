package com.siradze.topcontent.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.siradze.movies.MoviesNavigation
import com.siradze.movies.MoviesType

@Composable
fun MainNavigation(navController : NavHostController, modifier : Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "movies", modifier = modifier) {
        composable("movies") {
            MoviesNavigation(MoviesType.Movies)
        }
        composable("tv") {
            MoviesNavigation(MoviesType.TV)
        }

    }
}