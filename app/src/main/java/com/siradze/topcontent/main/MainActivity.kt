package com.siradze.topcontent.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.siradze.topcontent.main.navigation.BottomBar
import com.siradze.topcontent.main.navigation.MainNavigation
import com.siradze.topcontent.ui.theme.TopContentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TopContentTheme {

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,

                ) {
                    Scaffold(
                        Modifier.fillMaxSize(),
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    

                    ){
                        MainNavigation(navController, Modifier.padding(it))
                    }
                }
            }
        }
    }
}

