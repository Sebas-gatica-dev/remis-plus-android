package com.sgdev.emplag_lite.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sgdev.emplag_lite.ui.screens.home.HomeScreen
import com.sgdev.emplag_lite.ui.screens.home.HomeViewModel
import com.sgdev.emplag_lite.ui.screens.profile.ProfileScreen
import com.sgdev.emplag_lite.ui.screens.settings.SettingsScreen
import com.sgdev.emplag_lite.ui.screens.visits.VisitsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Visits : Screen("visits")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel,
            )

        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
        composable(Screen.Visits.route) {
            VisitsScreen(navController)
        }
    }
}