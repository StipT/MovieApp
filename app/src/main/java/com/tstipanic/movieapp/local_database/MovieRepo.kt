package com.tstipanic.movieapp.local_database

import com.tstipanic.movieapp.model.data.Movie

interface MovieRepo {

    fun addFavoriteMovie(movie: Movie)


    fun deleteFavoriteMovie(movie: Movie)


    fun getFavoriteMovies(userId: String): List<Movie>
}