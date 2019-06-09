package com.tstipanic.movieapp.ui.details_screen.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tstipanic.movieapp.model.data.Review
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_review.*

class ReviewViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindData(review: Review) {

        reviewAuthor.text = review.author
        reviewContent.text = review.content

    }

}