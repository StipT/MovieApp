package com.tstipanic.movieapp.model.response

import com.google.gson.annotations.SerializedName
import com.tstipanic.movieapp.model.data.Movie

class MoviesResponse(@SerializedName("results") val movies: ArrayList<Movie>)