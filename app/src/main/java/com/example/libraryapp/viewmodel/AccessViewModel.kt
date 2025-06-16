package com.example.libraryapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.network.ApiClient
import com.example.libraryapp.network.models.SmartLockDto
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class AccessViewModel : ViewModel() {

    suspend fun checkAccess(userId: Int, lockId: Int?): Boolean {
        if (lockId == null) return false

        return try {
            val locks = ApiClient.lockService.getLocks()
            val lock = locks.firstOrNull { it.lock_id == lockId }

            // Умова доступу — якщо замок активний
            lock?.status == true
        } catch (e: Exception) {
            Log.e("AccessViewModel", "Access check failed: ${e.message}")
            false
        }
    }
}
