package com.example.libraryapp.network.models

data class BorrowingDto(
    val br_id: Int? = null,
    val user_id: Int,
    val book_id: Int,
    val borrow_date: String,
    val return_date: String? = null,
    val status: String
)