package com.elmorshdi.movieexplorer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elmorshdi.movieexplorer.model.Item
import com.elmorshdi.movieexplorer.network.MovieService
import kotlinx.coroutines.*

class MoviesListViewModel : ViewModel() {
     val movieService = MovieService().getMovieService()
     var job: Job? = null
    var job2: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val movies = MutableLiveData<List<Item>>()
    val mostPopular=MutableLiveData<List<Item>>()
    val loadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchMovies()
    }

     fun fetchMovies(){
        loading.value = true

         job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = movieService.getMovies()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                     movies.value = response.body()?.items!!
                    loadError.value= response.body()!!.errorMessage.toString()
                    loading.value = false

                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
         job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
             val response = movieService.getMostPopularTVs()

             withContext(Dispatchers.Main) {
                 if (response.isSuccessful) {
                     mostPopular.value = response.body()?.items!!
                     loadError.value= response.body()!!.errorMessage.toString()
                     loading.value = false

                 } else {
                     onError("Error : ${response.message()} ")
                 }
             }
         }
       loadError.value = ""
        loading.value = false
    }

    private fun onError(message: String) {
       loadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        job2?.cancel()

    }

}