package com.example.roomsearch10.Api

import com.example.roomsearch10.Data.User
import com.example.roomsearch10.Data.UserResponse
import retrofit2.Call
import retrofit2.Response

class UserRepository {

    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun postUser(user: User): Call<User> {
        return RetrofitInstance.api.postData(user)
    }
}