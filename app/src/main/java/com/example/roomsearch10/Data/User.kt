package com.example.roomsearch10.Data

data class User(
    val _id: String,
    val email: String,
    val name: String,
    val phoneNo: String,
    val currentHostelBlock: String,
    val currentFloor: String,
    val desiredHostelBlock: String,
    val desiredFloor: String,
    val __v: Int
)

data class UserResponse(
    val users: List<User>
)

