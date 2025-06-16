package com.example.libraryapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libraryapp.viewmodel.AccessViewModel
import kotlinx.coroutines.launch

@Composable
fun AccessScreen(userId: Int, viewModel: AccessViewModel = viewModel()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var inputLockId by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("\uD83D\uDD11 Вхід у секцію (SmartLock)", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputLockId,
            onValueChange = { inputLockId = it },
            label = { Text("Введіть ID замка") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            coroutineScope.launch {
                val accessGranted = viewModel.checkAccess(userId, inputLockId.toIntOrNull())
                result = if (accessGranted) "✅ Доступ дозволено" else "⛔ Доступ заборонено"
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Перевірити доступ")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text(it)
        }
    }
}
