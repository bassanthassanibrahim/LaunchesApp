package com.launchapp.launches

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.launchapp.detail.LaunchDetailScreen
import com.launchapp.launches.feature_list.LaunchListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            LaunchListScreen(
                onNavigateToDetail = { launchId ->
                    navController.navigate("detail/$launchId")
                }
            )
        }
        composable(
            route = "detail/{launchId}",
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("launchId") ?: ""
            LaunchDetailScreen(
                launchId = id,
                onBack = { navController.popBackStack() }
            )
        }

    }
}