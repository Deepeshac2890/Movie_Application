package com.example.movie_application.screens.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie_application.model.Movie
import com.example.movie_application.model.getMovies
import com.example.movie_application.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.LightGray,) {
            Spacer(modifier = Modifier.weight(2f))
            Text(textAlign = TextAlign.Center, text = "Movie Viewer")
            Spacer(modifier = Modifier.weight(2f))
        }
    }, content = {
        // As this is function so we need to add () it won't error without ()
        // but won't work
        MainContent(navController = navController)
    })
}
@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Surface() {
        LazyColumn {
            items(items = movieList) {
                MovieRow(it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
                }
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit){
    val expanded = remember{
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(130.dp)
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ){
            Surface(
                Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
               ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
           Column(modifier = Modifier.padding(4.dp)) {
               Text(movie.title, style= MaterialTheme.typography.h6)
               Text("Director : ${movie.director}", style = MaterialTheme.typography.caption)
               Text("Released : ${movie.year.toInt() + 2000}" ,  style = MaterialTheme.typography.caption)
               AnimatedVisibility(visible = expanded.value) {
                   Column {
                       Text("Genre : ${movie.genre}", style = MaterialTheme.typography.caption)
                       Text("Plot : ${movie.plot}" ,  style = MaterialTheme.typography.caption)
                   }
               }
               Icon(imageVector = if (!expanded.value) Icons.Default.KeyboardArrowDown
                   else {
                   Icons.Default.KeyboardArrowUp
                        },
                   contentDescription = if (!expanded.value)  "Down Arrow"
                   else {
                       "Up Arrow"
                   },
                   modifier = Modifier
                       .size(25.dp)
                       .clickable {
                           expanded.value = !expanded.value
                       }, tint = Color.DarkGray)
           }
        }

    }
}
