package com.example.libraryapp.network

import com.example.libraryapp.network.models.SmartLockDto
import retrofit2.http.GET

interface SmartLockService {
    @GET("smartlocks")
    suspend fun getLocks(): List<SmartLockDto>
}
