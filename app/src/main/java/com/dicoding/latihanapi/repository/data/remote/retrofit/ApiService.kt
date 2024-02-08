package com.dicoding.latihanapi.repository.data.remote.retrofit

import com.dicoding.latihanapi.repository.data.remote.response.Heroes
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("marvel")
    fun getHeroes() : Call<ArrayList<Heroes>>
}