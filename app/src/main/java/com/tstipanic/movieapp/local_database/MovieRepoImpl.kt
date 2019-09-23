package com.tstipanic.movieapp.local_database

import com.tstipanic.movieapp.model.data.Movie

class MovieRepoImpl(db: MoviesDatabase) : MovieRepo {

    private var movieDao: MoviesDao = db.moviesDao()

    override fun addFavoriteMovie(movie: Movie) = movieDao.addFavoriteMovie(movie)

    override fun deleteFavoriteMovie(movie: Movie) = movieDao.deleteFavoriteMovie(movie)

    override fun getFavoriteMovies(userId: String): List<Movie> = movieDao.getFavoriteMovies(userId)
}