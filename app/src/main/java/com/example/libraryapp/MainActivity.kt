package com.example.libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.libraryapp.ui.LoginScreen
import com.example.libraryapp.ui.theme.LibraryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryAppTheme {
                var userId by remember { mutableStateOf<Int?>(null) }

                if (userId == null) {
                    LoginScreen(onLoginSuccess = { id -> userId = id })
                } else {
                    MainScreen(userId!!)
                }
            }
        }
    }
}
