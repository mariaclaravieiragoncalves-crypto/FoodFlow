package pt.clara.foodflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.clara.foodflow.screens.HomeScreen
import pt.clara.foodflow.screens.RestaurantDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        // --- Rota: Home ---
        composable(route = "home") {
            HomeScreen(
                onRestaurantClick = { restauranteId ->
                    navController.navigate("details/$restauranteId")
                }
            )
        }

        // --- Rota: Detalhes do Restaurante ---
        composable(
            route = "details/{restauranteId}",
            arguments = listOf(
                navArgument("restauranteId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val restauranteId = backStackEntry.arguments?.getString("restauranteId")

            if (restauranteId != null) {
                RestaurantDetailScreen(
                    restauranteId = restauranteId,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}