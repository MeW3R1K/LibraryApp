package com.example.libraryapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libraryapp.viewmodel.BooksViewModel

@Composable
fun BooksScreen(userId: Int, viewModel: BooksViewModel = viewModel()) {
    val books = viewModel.books
    val isLoading = viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.loadBooks()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("📚 Доступні книги", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(books) { book ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = book.title, style = MaterialTheme.typography.titleMedium)
                            Text(text = "Автор: ${book.author}")
                            Text(text = "Статус: ${book.status}")

                            Spacer(modifier = Modifier.height(8.dp))

                            val isEnabled = book.status == "available" || book.status == "borrowed"
                            Button(
                                onClick = {
                                    if (book.status == "available") {
                                        viewModel.borrowBook(userId, book.book_id)
                                    } else if (book.status == "borrowed") {
                                        viewModel.returnBook(userId, book.book_id)
                                    }
                                },
                                enabled = isEnabled
                            ) {
                                Text(
                                    text = when (book.status) {
                                        "available" -> "Бронювати"
                                        "borrowed" -> "Повернути"
                                        else -> "Недоступно"
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
