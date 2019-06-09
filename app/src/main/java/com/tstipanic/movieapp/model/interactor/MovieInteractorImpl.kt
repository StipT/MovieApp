package com.tstipanic.movieapp.model.interactor

import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.model.response.ReviewsResponse
import com.tstipanic.movieapp.networking.MovieApiService
import retrofit2.Callback

class MovieInteractorImpl(private val apiService: MovieApiService): MovieInteractor {
    override fun getPopularMovies(popularMoviesCallback: Callback<MoviesResponse>) {
        apiService.getPopularMovies().enqueue(popularMoviesCallback)
    }

    override fun getTopMovies(topMoviesCallback: Callback<MoviesResponse>) {
    apiService.getTopMovies().enqueue(topMoviesCallback)
    }

    override fun getMovie(movieId: Int, movieCallback: Callback<Movie>) {
    apiService.getMovie(movieId).enqueue(movieCallback)
    }

    override fun getMovieReview(movieId: Int, reviewCallback: Callback<ReviewsResponse>) {
    apiService.getReviews(movieId).enqueue(reviewCallback)
    }
}