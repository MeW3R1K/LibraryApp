package com.example.libraryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.libraryapp.viewmodel.RFIDScanViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun RFIDScanScreen(userId: Int, viewModel: RFIDScanViewModel = viewModel()) {
    var inputRfid by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top) {

        Text("📦 Емуляція сканування RFID", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputRfid,
            onValueChange = { inputRfid = it },
            label = { Text("Введіть RFID код") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.handleRfid(inputRfid, userId) { result ->
                message = result
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Сканувати")
        }

        Spacer(modifier = Modifier.height(16.dp))
        message?.let {
            Text(text = it, color = Color.DarkGray)
        }
    }
}
