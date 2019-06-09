package com.tstipanic.movieapp.local_database

import com.tstipanic.movieapp.MovieApp
import com.tstipanic.movieapp.model.data.Movie

class MovieRepoImpl: MovieRepo {

    private var db: MoviesDatabase = MoviesDatabase.getInstance(MovieApp.getAppContext())

    private var movieDao: MoviesDao = db.moviesDao()

    override fun addFavoriteMovie(movie: Movie) = movieDao.addFavoriteMovie(movie)

    override fun deleteFavoriteMovie(movie: Movie) = movieDao.deleteFavoriteMovie(movie)

    override fun getFavoriteMovies(): List<Movie> = movieDao.getFavoriteMovies()
}