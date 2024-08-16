package com.example.roomsearch10.Data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsearch10.Api.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

sealed class UserListState {
    object Loading : UserListState()
    data class Success(val users: List<User>) : UserListState()
    data class Error(val message: String) : UserListState()
}

sealed class UserPostState {
    object Loading : UserPostState()
    data class Success(val user: User) : UserPostState()
    data class Error(val message: String) : UserPostState()
}

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _userListState = MutableStateFlow<UserListState>(UserListState.Loading)
    val userResponse: StateFlow<UserListState> = _userListState

    private val _userPostState = MutableStateFlow<UserPostState>(UserPostState.Loading)
    val userPostState: StateFlow<UserPostState> = _userPostState

    init {
        fetchUsers(null,null)
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            // Reload data here
            fetchUsers(null,null)
            _isRefreshing.value = false
        }
    }

    private fun fetchUsers(selectedFloor: String?, selectedHostelBlock: String?) {
        viewModelScope.launch {
            try {
                val response = repository.getUsers()
                if (response.isSuccessful) {
                    // Filter users only if filters are applied
                    val users = response.body()?.filter { user ->
                        (selectedFloor == null || user.currentFloor == selectedFloor) &&
                                (selectedHostelBlock == null || user.currentHostelBlock.replace(" ", "").trim().let { current ->
                                    current == selectedHostelBlock || current.startsWith(selectedHostelBlock)
                                })
                    } ?: emptyList()

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

        fun postUser(user: User) {
            viewModelScope.launch {
                _userPostState.value = UserPostState.Loading
                try {
                    val response = repository.postUser(user).execute()
                    if (response.isSuccessful) {
                        val createdUser = response.body()
                        if (createdUser != null) {
                            _userPostState.value = UserPostState.Success(createdUser)
                        } else {
                            _userPostState.value = UserPostState.Error("User creation failed: Response body is null")
                        }
                    } else {
                        _userPostState.value = UserPostState.Error("Failed to create user: HTTP ${response.code()}")
                    }
                } catch (e: HttpException) {
                    _userPostState.value = UserPostState.Error("An error occurred: ${e.localizedMessage}")
                    Log.e("UserViewModel", "Error posting user", e)
                } catch (e: Exception) {
                    _userPostState.value = UserPostState.Error("An error occurred: ${e.localizedMessage}")
                    Log.e("UserViewModel", "Error posting user", e)
                }
            }
        }





}
