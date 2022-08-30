package com.siradze.topcontent.main.navigation

import androidx.annotation.DrawableRes
import com.siradze.topcontent.R


sealed class Screens(
    val route: String,
    val name : String,
    @DrawableRes val icon : Int
){
    object Movie : Screens(
        "movies",
        "Movies",
        R.drawable.movie_icon)

    object TVShow : Screens(
        "tv",
        "TV",
        R.drawable.tv_icon)


}
