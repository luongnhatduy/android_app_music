package com.example.android_app

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "c708dc0d96461643e1684200130c025d"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMG_URL = "https://image.tmdb.org/t/p/w500/"
const val GET_NOW_PLAYING: String = "movie/now_playing?api_key=${API_KEY}"
const val GET_MOVIE: String = "movie/{id}?api_key=${API_KEY}"

interface ApiService {

    @GET(GET_NOW_PLAYING)
    fun getNowPlaying(
        @Query("page") page: Int
    ) : Observable<LinkedTreeMap<String, Any>>

    @GET(GET_MOVIE)
    fun getMovie(
        @Path("id") id: String
    ) : Observable<LinkedTreeMap<String, Any>>

    companion object {
        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}