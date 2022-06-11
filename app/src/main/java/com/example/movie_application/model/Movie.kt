package com.example.movie_application.model

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val poster: String,
    val images: List<String>,
    val rating: String
    )

fun getMovies() : List<Movie> {
    val movieList = mutableListOf<Movie>()
    for (i in 0..10) {
        movieList.add(
            Movie(
                i.toString(),i.toString(),i.toString(),i.toString(),i.toString(),i.toString(),i.toString(),
            i.toString(),
            listOf("https://image.shutterstock.com/image-vector/online-cinema-art-movie-watching-600w-586719869.jpg"),i.toString()))
    }
    return movieList;
}
