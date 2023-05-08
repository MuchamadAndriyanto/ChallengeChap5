package com.example.bismillah.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bismillah.model.ListMovie
import com.example.bismillah.model.Result
import com.example.bismillah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel : ViewModel(){
    private val movietoprat: MutableLiveData<List<com.example.bismillah.model.Result>> by lazy {
        MutableLiveData<List<com.example.bismillah.model.Result>>().also {
            getAllMovietoprated()
        }
    }

    fun getMovies(): LiveData<List<Result>> {
        return movietoprat
    }

    private fun getAllMovietoprated() {
        RetrofitClient.instance.allMoviesTopRated().enqueue(object : Callback<ListMovie> {
            override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {

                movietoprat.value = response.body()?.results

            }

            override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                Log.d("Tag", t.message.toString())

            }

        })
    }
}