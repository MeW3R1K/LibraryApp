package com.example.libraryapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.network.models.BookDto
import com.example.libraryapp.network.ApiClient
import com.example.libraryapp.network.models.BorrowingDto
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.runtime.setValue

import java.time.LocalDate

class BooksViewModel : ViewModel() {
    var books by mutableStateOf<List<BookDto>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)

    fun loadBooks() {
        viewModelScope.launch {
            isLoading = true
            try {
                books = ApiClient.bookService.getBooks()
            } catch (e: Exception) {
                books = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
    fun borrowBook(userId: Int, bookId: Int) {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            try {
                val record = BorrowingDto(
                    user_id = userId,
                    book_id = bookId,
                    borrow_date = today,
                    status = "active"
                )
                ApiClient.borrowingService.createBorrowing(record)
                loadBooks()
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Помилка бронювання: ${e.message}")
            }
        }
    }

    fun returnBook(userId: Int, bookId: Int) {
        viewModelScope.launch {
            try {
                val all = ApiClient.borrowingService.getAll()
                val last = all.lastOrNull { it.book_id == bookId && it.user_id == userId && it.status == "active" }
                if (last != null) {
                    val updated = last.copy(
                        return_date = LocalDate.now().toString(),
                        status = "returned"
                    )
                    ApiClient.borrowingService.returnBorrowing(last.br_id!!, updated)
                }
                loadBooks()
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Помилка повернення: ${e.message}")
            }
        }
    }
}
