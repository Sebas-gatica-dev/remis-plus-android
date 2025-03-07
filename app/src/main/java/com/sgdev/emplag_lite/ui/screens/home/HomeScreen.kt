package com.sgdev.emplag_lite.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sgdev.emplag_lite.ui.components.AppScaffold
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.column.columnChart

import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf

import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    AppScaffold(
        navController = navController,
        title = uiState.title
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Metrics Cards (unchanged)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MetricCard(
                    title = "Empleados",
                    value = uiState.employeeCount.toString(),
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Productividad",
                    value = "${uiState.productivity.toInt()}%",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Line Chart
            ChartCard(
                title = "Ingresos Mensuales",
                chart = {
                    Chart(
                        chart = lineChart(),
                        model = entryModelOf(uiState.monthlyRevenue.mapIndexed { index, value ->
                            FloatEntry(x = index.toFloat(), y = value)
                        }),
                        startAxis = rememberStartAxis(
                            label = textComponent()
                        ),
                        bottomAxis = rememberBottomAxis(
                            label = textComponent()
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bar Chart
            ChartCard(
                title = "Productividad por Departamento",
                chart = {
                    Chart(
                        chart = columnChart(),
                        model = entryModelOf(uiState.departmentProductivity.mapIndexed { index, value ->
                            FloatEntry(x = index.toFloat(), y = value)
                        }),
                        startAxis = rememberStartAxis(
                            label = textComponent()
                        ),
                        bottomAxis = rememberBottomAxis(
                            label = textComponent()
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))



            // Recent Activity List (unchanged)
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Actividad Reciente",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    uiState.recentActivities.forEach { activity ->
                        ActivityItem(
                            title = activity.title,
                            description = activity.description
                        )
                    }
                }
            }
        }
    }
}

// MetricCard and ActivityItem components remain unchanged
@Composable
fun MetricCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun ChartCard(
    title: String,
    chart: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            chart()
        }
    }
}

@Composable
fun ActivityItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}