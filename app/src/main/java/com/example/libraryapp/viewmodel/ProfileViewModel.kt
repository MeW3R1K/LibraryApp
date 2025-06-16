package com.example.libraryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.network.ApiClient
import com.example.libraryapp.network.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _user = MutableStateFlow<UserResponse?>(null)
    val user: StateFlow<UserResponse?> = _user

    private val _activeBooksCount = MutableStateFlow(0)
    val activeBooksCount: StateFlow<Int> = _activeBooksCount

    fun loadProfile(userId: Int) {
        viewModelScope.launch {
            try {
                val users = ApiClient.userService.getAllUsers()
                _user.value = users.firstOrNull { it.user_id == userId }

                val borrowings = ApiClient.borrowingService.getAll()
                _activeBooksCount.value = borrowings.count {
                    it.user_id == userId && it.status == "active"
                }

            } catch (_: Exception) {
            }
        }
    }
}
