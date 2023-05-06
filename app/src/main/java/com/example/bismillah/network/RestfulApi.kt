package com.example.bismillah.network

import com.example.bismillah.model.ListMovie
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {
    @GET("3/movie/top_rated?api_key=5eadf04193e2a9bcea8a8e8f4cfa89e2&language=en-US&page=1")
    fun allMoviesTopRated(): Call<ListMovie>
}