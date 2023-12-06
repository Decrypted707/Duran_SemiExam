package com.duran.semiexam.network

import com.duran.semiexam.constants.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retro {

    private const val BASE_URL = Consts.API

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: API = retrofit.create(API::class.java)
}