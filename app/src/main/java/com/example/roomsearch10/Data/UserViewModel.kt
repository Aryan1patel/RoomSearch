package com.example.roomsearch10.Data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsearch10.Api.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UserListState {
    object Loading : UserListState()
    data class Success(val users: List<User>) : UserListState()
    data class Error(val message: String) : UserListState()
}

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _userListState = MutableStateFlow<UserListState>(UserListState.Loading)
    val userResponse: StateFlow<UserListState> = _userListState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = repository.getUsers()
                if (response.isSuccessful) {
                    // Assuming the response body directly contains the list of users
                    val users = response.body() ?: emptyList()
                    _userListState.value = UserListState.Success(users)
                } else {
                    _userListState.value = UserListState.Error("Failed to fetch users: HTTP ${response.code()}")
                }
            } catch (e: Exception) {
                _userListState.value = UserListState.Error("An error occurred: ${e.localizedMessage}")
                Log.e("UserViewModel", "Error fetching users", e)
            }
        }
    }
}
