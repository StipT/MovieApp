package com.tstipanic.movieapp.model.response

import com.google.gson.annotations.SerializedName
import com.tstipanic.movieapp.model.data.Review

data class ReviewsResponse(
    @SerializedName("results") val reviews: List<Review>
)