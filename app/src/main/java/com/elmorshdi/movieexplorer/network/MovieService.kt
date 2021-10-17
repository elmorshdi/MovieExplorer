package com.elmorshdi.movieexplorer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {
    private var BASE_URL = "https://imdb-api.com/en/API/"
    fun getMovieService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}