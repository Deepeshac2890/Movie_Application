package com.example.movie_application.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movie_application.model.Movie
import com.example.movie_application.model.getMovies


@Composable
fun DetailsScreen(navController: NavController,
                  movieId: String) {
    val movie : Movie
    movie = getMovies().filter {
        it.id == movieId
    }[0]
    Scaffold(
        topBar = {
            TopAppBar(title = {
                              Text(movie.title)
            }, navigationIcon = {
                Button(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
                }
            })

        }
    ) {
        androidx.compose.material.Surface(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text=(movie.year.toInt() + 2000).toString(), style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(23.dp))

            }
        }
    }

}