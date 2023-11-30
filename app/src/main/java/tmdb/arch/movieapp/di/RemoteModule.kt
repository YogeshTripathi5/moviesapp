package tmdb.arch.movieapp.di

import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tmdb.arch.movieapp.BuildConfig
import tmdb.arch.movieapp.domain.remote.MovieService
import tmdb.arch.movieapp.domain.repository.MoviesRepo
import java.util.concurrent.TimeUnit

val remoteModule
    get() = module {
        single {
            MoviesRepo(movieService)
        }
    }

private val httpClient
    get() = OkHttpClient.Builder()
        .callTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
                .header("accept", "application/json")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

private val retrofit
    get() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

private val movieService get() = retrofit.create(MovieService::class.java)
