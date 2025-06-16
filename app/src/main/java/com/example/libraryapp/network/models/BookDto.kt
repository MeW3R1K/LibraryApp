package com.example.libraryapp.network.models

data class BookDto(
    val book_id: Int,
    val title: String,
    val author: String,
    val status: String,
    val rfid_tag: String?
)
