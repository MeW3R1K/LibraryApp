package com.example.libraryapp.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// network/UserService.kt
interface UserService {
    @POST("users")
    suspend fun loginOrRegister(@Body body: Map<String, String>): UserResponse

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>

}

// network/models/UserResponse.kt
data class UserResponse(
    val user_id: Int,
    val name: String,
    val email: String,
    val role: String
)
