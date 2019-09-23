package com.tstipanic.movieapp.networking

import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.model.response.ReviewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopMovies(): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovie(@Path(value = "id") movieId: Int): Call<Movie>

    @GET("movie/{id}/reviews")
    fun getReviews(@Path(value = "id") movieId: Int): Call<ReviewsResponse>

    @GET("search/movie")
    fun getSearchResultMovies(@Query(value = "query") query: String): Call<MoviesResponse>
}