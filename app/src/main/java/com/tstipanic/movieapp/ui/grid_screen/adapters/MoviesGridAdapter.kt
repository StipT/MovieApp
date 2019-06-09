package com.tstipanic.movieapp.ui.grid_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.model.data.Movie

class MoviesGridAdapter(private val onMovieClickListener: (Int) -> Unit,
                        private val onFavoriteClickListener:(Movie) -> Unit) : RecyclerView.Adapter<MoviesGridViewHolder>(){

    private val movies = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesGridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesGridViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesGridViewHolder, position: Int) {
        holder.bindItem(movies[position], onMovieClickListener, onFavoriteClickListener)
    }
}