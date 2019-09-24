package com.tstipanic.movieapp.presentation

import com.google.firebase.auth.FirebaseAuth
import com.tstipanic.movieapp.local_database.MovieRepo
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.ui.search_screen.SearchContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(
    private val apiInteractor: MovieInteractor,
    private val appDatabase: MovieRepo
) :
    SearchContract.Presenter {

    private lateinit var view: SearchContract.View
    private val uid = FirebaseAuth.getInstance().uid!!
    private val movieList = arrayListOf<Movie>()


    override fun setView(view: SearchContract.View) {
        this.view = view
    }

    override fun getMovies(keyword: String) {
        apiInteractor.getSearchResultMovies(keyword, moviesCallback())
    }


    private fun moviesCallback(): Callback<MoviesResponse> = object : Callback<MoviesResponse> {
        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
            view.showToast("Something went wrong")
            view.showNoResultImage()
            view.hideProgress()
        }

        override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
            if (response.isSuccessful) {
                view.hideNoResultImage()
                response.body()?.movies?.run {
                    movieList.clear()
                    movieList.addAll(favoriteParse(this))
                    view.setRecyclerList(this)
                    view.setRecyclerList(favoriteParse(this))
                    if (this.isEmpty()) {
                        view.showNoResultImage()
                    }

                }
                view.hideProgress()
            }
        }
    }

    override fun onIntent(movie: Movie) {
        view.initiateIntent(movie)
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
        view.refreshList()
    }

    private fun favoriteParse(movieList: List<Movie>): List<Movie> {
        val favoriteMovies = appDatabase.getFavoriteMovies(uid)
        for (movie in movieList) {
            movie.userId = uid
            if (favoriteMovies.contains(movie)) movie.isFavorite = true
        }
        return movieList
    }
}