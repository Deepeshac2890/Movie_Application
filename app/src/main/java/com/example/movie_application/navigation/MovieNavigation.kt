package com.example.movie_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie_application.screens.home.DetailsScreen
import com.example.movie_application.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            // here we pass where this should lead us to
            HomeScreen(navController)
        }

        composable(MovieScreens.DetailsScreen.name+"/{movie}") {
            it.arguments?.getString("movie")?.let { it1 -> DetailsScreen(navController, it1) }
        }
    }
}
