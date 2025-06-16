package com.example.libraryapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.libraryapp.network.ApiClient
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue



class LoginViewModel : ViewModel() {
    suspend fun loginOrRegister(email: String): Int? {
        return try {
            val response = ApiClient.userService.loginOrRegister(mapOf("email" to email))
            response.user_id
        } catch (e: Exception) {
            null
        }
    }
}
