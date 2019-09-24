package com.tstipanic.movieapp.ui.details_screen

import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.data.Review

interface MovieDetailsContract {

    interface View {
        fun setReviewList(list: List<Review>)

        fun onReviewCallbackFailure(t: Throwable)

        fun setUnfavoriteIcon()

        fun setFavoriteIcon()

        fun showNoReviews()

        fun hideNoReviews()

    }

    interface Presenter {
        fun setView(view: View)

        fun getReviews(movie: Movie)

        fun onFavoriteClicked(movie: Movie)
    }
}