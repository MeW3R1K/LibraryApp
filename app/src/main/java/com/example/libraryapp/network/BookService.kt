package com.example.libraryapp.network

import com.example.libraryapp.network.models.BookDto
import retrofit2.http.GET

interface BookService {
    @GET("books")
    suspend fun getBooks(): List<BookDto>
}

val bookService: BookService = ApiClient.retrofit.create(BookService::class.java)