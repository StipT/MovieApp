package com.tstipanic.movieapp.ui.grid_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.showFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showGridFragment()
    }

    private fun showGridFragment() {
        showFragment(R.id.mainFragmentHolder, MovieGridFragment())
    }

}


