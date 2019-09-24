package com.tstipanic.movieapp.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(IMAGE_URL + imageUrl).into(this)
}

fun FragmentActivity.showFragment(
    containerId: Int,
    fragment: Fragment,
    shouldAddToBackStack: Boolean = false,
    tag: String = ""
) {
    supportFragmentManager.beginTransaction().apply {
        if (shouldAddToBackStack) {
            addToBackStack(tag)
        }
    }.replace(containerId, fragment).commitAllowingStateLoss()}
