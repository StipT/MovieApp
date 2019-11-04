package com.tstipanic.movieapp.ui.grid_screen.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.loadImage
import com.tstipanic.movieapp.model.data.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*


class MoviesGridViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindItem(movie: Movie, onMovieClickListener: (Int) -> Unit, onFavoriteClickListener: (Movie) -> Unit) {
        movieItemImage.loadImage(movie.poster)
        movieFavorite.setImageResource(if (movie.isFavorite) R.drawable.ic_favorite_full else R.drawable.ic_favorite_empty)
        containerView.setOnClickListener {
            onMovieClickListener(adapterPosition)
        }

        movieFavorite.setOnClickListener {
            onFavoriteClickListener(movie)
        }
    }

}