package com.tstipanic.movieapp.networking

import com.tstipanic.movieapp.common.API_KEY
import com.tstipanic.movieapp.common.AUTHENTICATION
import com.tstipanic.movieapp.common.BASE_URL
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.interactor.MovieInteractorImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendFactory {

    private var retrofit: Retrofit? = null
    private var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun provideAuthenticatiionInterceptor() = Interceptor {
        var request = it.request()
        val url = request.url().newBuilder().addQueryParameter(AUTHENTICATION, API_KEY).build()
        request = request.newBuilder().url(url).build()
        it.proceed(request)
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(provideAuthenticatiionInterceptor())
        .build()

    private val client: Retrofit? = if (retrofit == null) createRetrofit() else retrofit

    private fun createRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return  retrofit
    }


    private fun getService(): MovieApiService =this.client!!.create(MovieApiService::class.java)

    fun getMovieInteractor(): MovieInteractor = MovieInteractorImpl(getService())
}