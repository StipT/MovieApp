package com.tstipanic.movieapp.ui.search_screen.search_recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.loadImage
import com.tstipanic.movieapp.model.data.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_search.*

class SearchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindData(
        movie: Movie,
        onMovieClickListener: (Movie, ImageView) -> Unit,
        onFavoriteClickListener: (Movie) -> Unit
    ) {
        searchMovieTitleText.text = movie.title
        searchRatingText.text = movie.averageVote.toString()
        searchDateText.text = movie.releaseDate
        searchFavorite.setImageResource(if (movie.isFavorite) R.drawable.ic_favorite_full else R.drawable.ic_favorite_empty)
        if (movie.poster != null) {
            searchMovieImage.loadImage(movie.poster)
        }

        containerView.setOnClickListener { onMovieClickListener(movie, searchMovieImage) }

        searchFavorite.setOnClickListener {
            onFavoriteClickListener(movie)
        }
    }
}
