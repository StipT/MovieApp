package com.tstipanic.movieapp

import android.app.Application
import android.content.Context
import com.tstipanic.movieapp.di.databaseModule
import com.tstipanic.movieapp.di.networkingModule
import com.tstipanic.movieapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {

    companion object {
        private lateinit var instance: MovieApp

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

        startKoin {
            modules(listOf(networkingModule, presentationModule, databaseModule))
            androidContext(this@MovieApp)
        }
    }
}