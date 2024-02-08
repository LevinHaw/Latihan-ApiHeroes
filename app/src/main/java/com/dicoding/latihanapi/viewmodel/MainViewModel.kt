package com.dicoding.latihanapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.latihanapi.repository.data.remote.response.Heroes
import com.dicoding.latihanapi.repository.data.remote.retrofit.ApiClient
import com.dicoding.latihanapi.repository.data.remote.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val listHero = MutableLiveData<ArrayList<Heroes>>()
    val getListHero: LiveData<ArrayList<Heroes>> = listHero

    private val isLoading = MutableLiveData<Boolean>()
    val getIsLoading: LiveData<Boolean> = isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        getHero()
    }

    fun getHero() {
        try {
            isLoading.value = true
            val client : ApiService = ApiClient.getClient().create(ApiService::class.java)
            val call : Call<ArrayList<Heroes>> = client.getHeroes()
            call.enqueue(object : Callback<ArrayList<Heroes>>{
                override fun onResponse(
                    call: Call<ArrayList<Heroes>>,
                    response: Response<ArrayList<Heroes>>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful){
                        listHero.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Heroes>>, t: Throwable) {
                    Log.d(TAG, "Gagal Fetch Data Superhero")
                }


            })

        } catch (e : Exception) {
            Log.e(TAG, e.toString())

        }
    }
}