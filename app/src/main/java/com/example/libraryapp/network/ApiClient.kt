package com.example.libraryapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/") // для емулятора Android
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService: UserService = retrofit.create(UserService::class.java)
    val bookService: BookService = retrofit.create(BookService::class.java)
    val borrowingService: BorrowingService = retrofit.create(BorrowingService::class.java)
    val lockService: SmartLockService by lazy {
        retrofit.create(SmartLockService::class.java)
    }


}