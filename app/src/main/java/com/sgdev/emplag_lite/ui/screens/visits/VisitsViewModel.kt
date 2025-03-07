package com.sgdev.emplag_lite.ui.screens.visits

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class VisitsUiState(
    val title: String = "Rutas diarias",
    val isLoading: Boolean = false,
    val currentLocation: LatLng = LatLng(0.0, 0.0)

)

class VisitsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VisitsUiState())
    val uiState: StateFlow<VisitsUiState> = _uiState.asStateFlow()

    fun updateCurrentLocation(location: LatLng) {
        viewModelScope.launch{
            _uiState.value = _uiState.value.copy(currentLocation = location)
        }
    }
}