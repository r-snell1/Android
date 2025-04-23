package com.r.snell.flightsearch.ui.state

import com.r.snell.flightsearch.data.local.entity.Airport
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import com.r.snell.flightsearch.domain.model.FlightWithAirportDetails

data class FlightUiState(
    val searchQuery: String = "",
    val suggestions: List<Airport> = emptyList(),
    val flights: List<FlightWithAirportDetails> = emptyList(),
    val favoriteRoutes: List<FavoriteRoute> = emptyList(),
    val isLoading: Boolean = false
) {
    fun isFavorited(from: String, to: String): Boolean {
        return favoriteRoutes.any { it.departureCode == from && it.destinationCode == to }
    }
}