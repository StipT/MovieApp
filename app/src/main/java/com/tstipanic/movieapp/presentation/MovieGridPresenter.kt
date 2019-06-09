package com.tstipanic.movieapp.presentation

import com.tstipanic.movieapp.local_database.MovieRepoImpl
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.networking.BackendFactory
import com.tstipanic.movieapp.ui.grid_screen.MovieGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieGridPresenter : MovieGridContract.Presenter {

    private lateinit var view: MovieGridContract.View
    private val apiInteractor: MovieInteractor by lazy { BackendFactory.getMovieInteractor() }
    private val movieList = arrayListOf<Movie>()
    private val appDatabase by lazy { MovieRepoImpl() }


    override fun setView(view: MovieGridContract.View) {
        this.view = view
    }

    override fun onPopularMenuSelected() {
        apiInteractor.getPopularMovies(moviesCallback())
    }


    override fun onTopRatedMenuSelected() {
        apiInteractor.getTopMovies(moviesCallback())
    }

    override fun onFavoriteMenuSelected() {
        movieList.clear()
        movieList.addAll(favoriteParse(appDatabase.getFavoriteMovies()))
        view.setGridList(movieList)
    }


    private fun moviesCallback(): Callback<MoviesResponse> = object : Callback<MoviesResponse> {
        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
            view.onMovieCallbackFailure(t)
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
            appDatabase.addFavoriteMovie(movie)
        }
        view.refreshGrid()
    }


    private fun favoriteParse(movieList: List<Movie>): List<Movie> {
        val favoriteMovies = appDatabase.getFavoriteMovies()
        for (movie in movieList) {
            if (favoriteMovies.contains(movie)) movie.isFavorite = true
        }
        return movieList
    }

    override fun getMovieList(): ArrayList<Movie> = movieList
}