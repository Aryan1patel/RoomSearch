package com.example.roomsearch10.Data

data class User(
    val email: String,
    val name: String,
    val phoneNo: String,
    val currentHostelBlock: String,
    val currentFloor: String,
    val desiredHostelBlock: String,
    val desiredFloor: String,
)

data class UserResponse(
    val users: List<User>
)

