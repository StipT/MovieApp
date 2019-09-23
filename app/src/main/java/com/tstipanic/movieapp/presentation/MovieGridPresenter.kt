package com.tstipanic.movieapp.presentation

import com.google.firebase.auth.FirebaseAuth
import com.tstipanic.movieapp.local_database.MovieRepo
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.ui.grid_screen.MovieGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieGridPresenter(
    private val apiInteractor: MovieInteractor,
    private val appDatabase: MovieRepo
) : MovieGridContract.Presenter {

    private lateinit var view: MovieGridContract.View

    private val movieList = arrayListOf<Movie>()
    private val uid = FirebaseAuth.getInstance().uid!!
    private var firstOnResume = true

    override fun setView(view: MovieGridContract.View) {
        this.view = view
    }

    override fun onPopularMenuSelected() {
        view.showProgress()

        apiInteractor.getPopularMovies(moviesCallback())
    }


    override fun onTopRatedMenuSelected() {
        view.showProgress()
        apiInteractor.getTopMovies(moviesCallback())
    }

    override fun onFavoriteMenuSelected() {
        movieList.clear()
        movieList.addAll(favoriteParse(appDatabase.getFavoriteMovies(uid)))
        view.setGridList(movieList)
    }


    private fun moviesCallback(): Callback<MoviesResponse> = object : Callback<MoviesResponse> {
        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
            view.onMovieCallbackFailure(t)
            view.hideProgress()
        }

        override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
            if (response.isSuccessful) {
                response.body()?.movies?.run {
                    movieList.clear()
                    movieList.addAll(favoriteParse(this))
                    view.setGridList(this)
                    view.scrollOnTop()
                }
            }
            view.hideProgress()
        }
    }

    override fun onFavoriteClicked(movie: Movie) {

        if (movie.isFavorite) {
            movie.isFavorite = false
            appDatabase.deleteFavoriteMovie(movie)
            view.setUnfavoriteIcon()
            view.bottomNavState()

        } else {
            movie.isFavorite = true
            view.setFavoriteIcon()
            movie.userId = uid
            appDatabase.addFavoriteMovie(movie)
        }
        view.refreshGrid()
    }

    override fun onResume() {
        if (firstOnResume) {
            onPopularMenuSelected()
            firstOnResume = false
        }
    }

    private fun favoriteParse(movieList: List<Movie>): List<Movie> {
        val favoriteMovies = appDatabase.getFavoriteMovies(uid)
        for (movie in movieList) {
            movie.userId = uid
            if (favoriteMovies.contains(movie)) movie.isFavorite = true
        }
        return movieList
    }

    override fun getMovieList(): ArrayList<Movie> = movieList
}