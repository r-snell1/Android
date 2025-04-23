package com.r.snell.flightsearch.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.r.snell.flightsearch.ui.screen.FlightSearchScreen
import com.r.snell.flightsearch.ui.screen.FavoritesScreen

object Destinations {
    const val FLIGHT_SEARCH = "flight_search"
    const val FAVORITES = "favorites"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.FLIGHT_SEARCH
    ) {
        composable(Destinations.FLIGHT_SEARCH) {
            FlightSearchScreen()
        }
        composable(Destinations.FAVORITES) {
            FavoritesScreen()
        }
    }
}