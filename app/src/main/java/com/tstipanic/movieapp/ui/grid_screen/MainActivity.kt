package com.tstipanic.movieapp.ui.grid_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.showFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMoviesGridFragment()
    }

    private fun initMoviesGridFragment() {
        showFragment(R.id.mainFragmentHolder, MovieGridFragment())
    }
}


