package com.elmorshdi.movieexplorer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elmorshdi.movieexplorer.R
import com.elmorshdi.movieexplorer.adapter.MovieAdapterHor
import com.elmorshdi.movieexplorer.adapter.MovieAdapterVer
import com.elmorshdi.movieexplorer.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}