package com.tstipanic.movieapp.di


import com.tstipanic.movieapp.common.API_KEY
import com.tstipanic.movieapp.common.AUTHENTICATION
import com.tstipanic.movieapp.common.BASE_URL
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.interactor.MovieInteractorImpl
import com.tstipanic.movieapp.networking.MovieApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOGGING_INTERCEPTOR = "logging"
const val AUTH_INTERCEPTOR = "auth"


val networkingModule = module {

    single(named(LOGGING_INTERCEPTOR)) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }

    single(named(AUTH_INTERCEPTOR)) {
        Interceptor {
            var request = it.request()
            val url = request.url().newBuilder().addQueryParameter(AUTHENTICATION, API_KEY).build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get(named(AUTH_INTERCEPTOR)))
            .addInterceptor(get(named(LOGGING_INTERCEPTOR)))
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MovieApiService::class.java) }

    factory<MovieInteractor> { MovieInteractorImpl(get()) }
}
