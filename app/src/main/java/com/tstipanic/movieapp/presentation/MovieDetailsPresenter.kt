package com.tstipanic.movieapp.presentation

import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.response.ReviewsResponse
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsPresenter(private val apiInteractor: MovieInteractor) : MovieDetailsContract.Presenter {

    private lateinit var view: MovieDetailsContract.View


    override fun setView(view: MovieDetailsContract.View) {
       this.view = view
    }

    override fun getReviews(movie: Movie){
        apiInteractor.getMovieReview(movie.id, reviewsCallback())
    }

    private fun reviewsCallback(): Callback<ReviewsResponse> = object : Callback<ReviewsResponse> {
        override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
            view.onReviewCallbackFailure(t)
        }

        override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
            if (response.isSuccessful){
                response.body()?.reviews?.run {
                    view.setReviewList(this)
                }
            }
        }
    }


}