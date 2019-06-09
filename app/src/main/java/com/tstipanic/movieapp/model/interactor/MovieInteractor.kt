package com.tstipanic.movieapp.model.interactor

import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.model.response.ReviewsResponse
import retrofit2.Callback

interface MovieInteractor {

    fun getPopularMovies(popularMoviesCallback: Callback<MoviesResponse>)

    fun getTopMovies(topMoviesCallback: Callback<MoviesResponse>)

    fun getMovie(movieId: Int, movieCallback: Callback<Movie>)

    fun getMovieReview(movieId: Int, reviewCallback: Callback<ReviewsResponse>)
}