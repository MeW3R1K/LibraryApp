package com.example.libraryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.network.ApiClient
import com.example.libraryapp.network.models.BorrowingDto
import kotlinx.coroutines.launch
import java.time.LocalDate

class RFIDScanViewModel : ViewModel() {

    fun handleRfid(rfid: String, userId: Int, onResult: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val books = ApiClient.bookService.getBooks()
                val book = books.firstOrNull { it.rfid_tag == rfid }

                if (book == null) {
                    onResult("Книгу не знайдено для RFID: $rfid")
                    return@launch
                }

                if (book.status == "available") {
                    // Бронюємо
                    ApiClient.borrowingService.createBorrowing(
                        BorrowingDto(
                            user_id = userId,
                            book_id = book.book_id,
                            borrow_date = LocalDate.now().toString(),
                            status = "active"
                        )
                    )
                    onResult("✅ Книгу \"${book.title}\" заброньовано")
                } else if (book.status == "borrowed") {
                    // Повернення
                    val all = ApiClient.borrowingService.getAll()
                    val record = all.lastOrNull {
                        it.book_id == book.book_id && it.user_id == userId && it.status == "active"
                    }

                    if (record != null) {
                        val updated = record.copy(
                            return_date = LocalDate.now().toString(),
                            status = "returned"
                        )
                        ApiClient.borrowingService.returnBorrowing(record.br_id!!, updated)
                        onResult("✅ Книгу \"${book.title}\" повернено")
                    } else {
                        onResult("⚠️ Ви не брали цю книгу")
                    }
                } else {
                    onResult("⛔ Книга недоступна")
                }

            } catch (e: Exception) {
                onResult("❌ Помилка: ${e.message}")
            }
        }
    }
}
