package com.tstipanic.movieapp.networking

import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.model.response.ReviewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("popular")
    fun getPopularMovies(): Call<MoviesResponse>

    @GET("top_rated")
    fun getTopMovies(): Call<MoviesResponse>

    @GET("{id}")
    fun getMovie(@Path(value = "id") movieId: Int): Call<Movie>

    @GET("{id}/reviews")
    fun getReviews(@Path(value = "id") movieId: Int): Call<ReviewsResponse>
}