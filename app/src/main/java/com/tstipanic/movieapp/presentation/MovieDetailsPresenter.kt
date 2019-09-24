package com.tstipanic.movieapp.presentation

import com.google.firebase.auth.FirebaseAuth
import com.tstipanic.movieapp.local_database.MovieRepo
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.response.ReviewsResponse
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsPresenter(
    private val apiInteractor: MovieInteractor,
    private val appDatabase: MovieRepo
) : MovieDetailsContract.Presenter {

    private lateinit var view: MovieDetailsContract.View
    private val uid = FirebaseAuth.getInstance().uid!!

    override fun setView(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun getReviews(movie: Movie){
        apiInteractor.getMovieReview(movie.id, reviewsCallback())
    }

    override fun onFavoriteClicked(movie: Movie) {

        if (movie.isFavorite) {
            movie.isFavorite = false
            appDatabase.deleteFavoriteMovie(movie)
            view.setUnfavoriteIcon()

        } else {
            movie.isFavorite = true
            view.setFavoriteIcon()
            movie.userId = uid
            appDatabase.addFavoriteMovie(movie)
        }
    }


    private fun reviewsCallback(): Callback<ReviewsResponse> = object : Callback<ReviewsResponse> {
        override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
            view.onReviewCallbackFailure(t)
        }

        override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
            if (response.isSuccessful){
                response.body()?.reviews?.run {
                    if (!this.isNullOrEmpty()) {
                        view.setReviewList(this)
                    } else {
                        view.showNoReviews()
                    }
                }
            }
        }
    }


}