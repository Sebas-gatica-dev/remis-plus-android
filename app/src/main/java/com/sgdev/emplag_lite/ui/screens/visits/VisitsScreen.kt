package com.sgdev.emplag_lite.ui.screens.visits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sgdev.emplag_lite.ui.components.AppScaffold
import com.sgdev.emplag_lite.ui.components.GoogleMapComponent

data class Visit(val id: Int, val date: String, val location: String, val status: String)

@Composable
fun VisitsScreen(
    navController: NavController,
    viewModel: VisitsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val visits = listOf(
        Visit(1, "2023-11-10", "Ciudad A", "Completada"),
        Visit(2, "2023-11-11", "Ciudad B", "Pendiente"),
        Visit(3, "2023-11-12", "Ciudad C", "En progreso"),
        Visit(4, "2023-11-13", "Ciudad D", "Cancelada"),
        Visit(5, "2023-11-14", "Ciudad E", "Completada")
    )

    AppScaffold(
        navController = navController,
        title = uiState.title
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GoogleMapComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                initialLocation = uiState.currentLocation,
                onMapClick = { latLng ->
                    viewModel.updateCurrentLocation(latLng)
                }
            )
            Text(
                text = "Registro de Visitas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    TableRow(
                        cells = listOf("ID", "Fecha", "Ubicación", "Estado"),
                        weights = listOf(1f, 2f, 3f, 2f),
                        isHeader = true
                    )
                }

                items(visits) { visit ->
                    TableRow(
                        cells = listOf(visit.id.toString(), visit.date, visit.location, visit.status),
                        weights = listOf(1f, 2f, 3f, 2f),
                        isHeader = false
                    )
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}

@Composable
fun TableRow(cells: List<String>, weights: List<Float>, isHeader: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isHeader) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface)
    ) {
        cells.forEachIndexed { index, cell ->
            TableCell(text = cell, weight = weights[index])
        }
    }
}

@Composable
fun RowScope.TableCell(text: String, weight: Float) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}