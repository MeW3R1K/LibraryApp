package com.example.libraryapp.network

import com.example.libraryapp.network.models.BorrowingDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BorrowingService {
    @POST("borrowings")
    suspend fun createBorrowing(@Body data: BorrowingDto): BorrowingDto

    @PUT("borrowings/{id}")
    suspend fun returnBorrowing(@Path("id") id: Int, @Body data: BorrowingDto): BorrowingDto

    @GET("borrowings")
    suspend fun getAll(): List<BorrowingDto>
}

val borrowingService: BorrowingService = ApiClient.retrofit.create(BorrowingService::class.java)