package com.example.roomsearch10.Api

import com.example.roomsearch10.Data.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @POST("submit")
    suspend fun postData(@Body dataModel: User): Call<User>
}