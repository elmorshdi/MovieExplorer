package com.elmorshdi.movieexplorer.network

import com.elmorshdi.movieexplorer.model.Movies
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("ComingSoon/k_ck0qo9qt")
    suspend fun getMovies(): Response<Movies>

}

