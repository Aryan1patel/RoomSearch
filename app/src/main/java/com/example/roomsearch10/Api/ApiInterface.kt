package com.example.roomsearch10.Api

import com.example.roomsearch10.Data.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}