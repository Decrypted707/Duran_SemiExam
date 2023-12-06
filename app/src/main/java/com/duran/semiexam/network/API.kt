package com.duran.semiexam.network

import com.duran.semiexam.models.Model
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path


interface API {

    @GET("/tweet/duran/")
    fun getPostList(): Call<List<Model>>

    @GET("/tweet/duran/{id}")
    fun getPostById(@Path("id") id: String): Call<Model>

    @POST("/tweet/duran/")
    fun createPost(@Body post: Model): Call<Model>

    @DELETE("/tweet/duran/{id}")
    fun deletePostById(@Path("id") id: String): Call<Model>

    @PUT("/tweet/duran/{id}")
    fun updateTweet(@Path("id") id: Model, @Body updatedTweet: String): Call<Model>
    abstract fun deleteTweetById(tweetId: String): Any
}