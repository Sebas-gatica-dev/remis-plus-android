package com.sgdev.emplag_lite.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapComponent(
    modifier: Modifier = Modifier,
    initialLocation: LatLng = LatLng(-58.3455288,-34.7645566 ),
    initialZoom: Float = 15f,
    onMapClick: ((LatLng) -> Unit)? = null,
    showMarker: Boolean = true,
    markerTitle: String? = null,
    markerSnippet: String? = null
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLocation, initialZoom)
    }

    GoogleMap(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            onMapClick?.invoke(latLng)
        }
    ) {
        if (showMarker) {
            Marker(
                state = MarkerState(position = initialLocation),
                title = markerTitle,
                snippet = markerSnippet
            )
        }
    }
}