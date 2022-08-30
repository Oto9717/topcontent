package com.siradze.movies.data.api

import com.siradze.movies.BuildConfig

object ApiStrings {
    const val base_url =  BuildConfig.BASE_URL
    const val api_key = BuildConfig.API_KEY
    const val preview_image_url =  BuildConfig.IMAGE_URL + "w200/"
    const val original_image_url = BuildConfig.IMAGE_URL + "original/"
}