package com.example.libraryapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libraryapp.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(userId: Int, viewModel: ProfileViewModel = viewModel()) {
    val user by viewModel.user.collectAsState()
    val activeCount by viewModel.activeBooksCount.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadProfile(userId)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("👤 Профіль", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        user?.let {
            Text("Ім’я: ${it.name}")
            Text("Email: ${it.email}")
            Text("Роль: ${it.role}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("📚 Активних книг: $activeCount")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* Очистити userId, перезапуск */ }, enabled = false) {
            Text("🚪 Вийти (заглушка)")
        }
    }
}
