package com.example.libraryapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// MainScreen.kt
@Composable
fun MainScreen(userId: Int) {
    val navController = rememberNavController()
    val items = listOf("books", "scan", "access", "profile")

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = navController.currentBackStackEntryAsState().value?.destination?.route == screen,
                        onClick = { navController.navigate(screen) },
                        icon = {
                            Icon(
                                imageVector = when (screen) {
                                    "books" -> Icons.Default.MenuBook
                                    "scan" -> Icons.Default.QrCodeScanner
                                    "access" -> Icons.Default.VpnKey
                                    "profile" -> Icons.Default.Person
                                    else -> Icons.Default.Info
                                },
                                contentDescription = screen
                            )
                        },
                        label = { Text(screen.capitalize()) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "books",
            modifier = Modifier.padding(padding)
        ) {
            composable("books") { BooksScreen(userId) }
            composable("scan") { RFIDScanScreen(userId) }
            composable("access") { AccessScreen(userId) }
            composable("profile") { ProfileScreen(userId) }
        }
    }
}

@Composable
fun BooksScreen(userId: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("📚 Список книг для користувача ID $userId")
    }
}

@Composable
fun RFIDScanScreen(userId: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("📦 Емуляція сканування RFID")
    }
}

@Composable
fun AccessScreen(userId: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("🔓 Перевірка доступу до секцій")
    }
}

@Composable
fun ProfileScreen(userId: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("👤 Профіль користувача")
    }
}
