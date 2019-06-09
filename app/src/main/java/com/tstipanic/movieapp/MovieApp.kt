package com.tstipanic.movieapp

import android.app.Application
import android.content.Context

class MovieApp: Application() {

    companion object {
        private lateinit var instance: MovieApp

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}