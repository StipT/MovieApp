package com.tstipanic.movieapp.ui.search_screen

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.EXTRA_BOOLEAN
import com.tstipanic.movieapp.common.EXTRA_IMAGE_TRANSITION_NAME
import com.tstipanic.movieapp.common.EXTRA_OBJECT
import com.tstipanic.movieapp.common.loadImage
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.data.Review
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsContract
import com.tstipanic.movieapp.ui.details_screen.adapters.ReviewAdapter
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.ext.android.inject

class ResultActivity : AppCompatActivity(), MovieDetailsContract.View {

    private val presenter by inject<MovieDetailsContract.Presenter>()
    private val reviewsAdapter by lazy { ReviewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_details)
        val movie = intent?.extras?.getParcelable<Movie>(EXTRA_OBJECT)!!
        val isFavorite = intent?.extras?.getBoolean(EXTRA_BOOLEAN)
        movie.isFavorite = isFavorite!!
        initUi(movie)
        presenter.getReviews(movie)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transitionExtra = intent.extras.getString(EXTRA_IMAGE_TRANSITION_NAME)
            movieImagePoster.transitionName = transitionExtra

        }
    }



    override fun setReviewList(list: List<Review>) {
        reviewsAdapter.setReviewList(list)
    }

    override fun onReviewCallbackFailure(t: Throwable) {
        t.printStackTrace()
        //TODO toast
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    private fun initUi(movie: Movie) {
        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieReleaseDate.text = movie.releaseDate
        movieVoteAverage.text = movie.averageVote.toString()
        movieImagePoster.loadImage(movie.poster)
        movieFavoriteIcon.setOnClickListener { presenter.onFavoriteClicked(movie) }
        if (movie.isFavorite) {
            movieFavoriteIcon.setImageResource(R.drawable.ic_favorite_full)
        } else {
            movieFavoriteIcon.setImageResource(R.drawable.ic_favorite_empty)
        }

        movieReviewList.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }


    override fun setFavoriteIcon() {
        movieFavoriteIcon.setImageResource(R.drawable.ic_favorite_full)
    }

    override fun setUnfavoriteIcon() {
        movieFavoriteIcon.setImageResource(R.drawable.ic_favorite_empty)
    }

    override fun showNoReviews() {
        noReviewsText.visibility = View.VISIBLE
    }

    override fun hideNoReviews() {
        noReviewsText.visibility = View.INVISIBLE
    }
}

