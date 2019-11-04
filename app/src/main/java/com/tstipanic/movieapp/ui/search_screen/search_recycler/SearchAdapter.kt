package com.tstipanic.movieapp.ui.search_screen.search_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.model.data.Movie
import kotlinx.android.synthetic.main.item_search.*

class SearchAdapter(
    private val onMovieClickListener: (Movie, ImageView) -> Unit,
    private val onFavoriteClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<SearchViewHolder>() {

    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        ViewCompat.setTransitionName(holder.searchMovieImage, movies[position].title)
        holder.bindData(movies[position], onMovieClickListener, onFavoriteClickListener)
    }

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}