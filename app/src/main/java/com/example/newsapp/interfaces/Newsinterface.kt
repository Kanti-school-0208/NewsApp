package com.example.newsapp.interfaces

import com.example.newsapp.Newsgrp
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//"https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2a78e53bb38e45c398ad587369669654"

 const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "2a78e53bb38e45c398ad587369669654"
interface Newsinterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getnews(@Query("country")country : String,@Query("category")category : String,@Query("page")page : Int) : Call<Newsgrp>
}

object Newsservies{
    val newsinstance : Newsinterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsinstance = retrofit.create(Newsinterface::class.java)
    }
}