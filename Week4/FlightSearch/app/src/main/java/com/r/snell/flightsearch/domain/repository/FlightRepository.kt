package com.r.snell.flightsearch.domain.repository

import com.r.snell.flightsearch.data.local.entity.Airport
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun searchAirports(query: String): Flow<List<Airport>>
    fun getAllAirports(): Flow<List<Airport>>
    fun getFavoriteRoutes(): Flow<List<FavoriteRoute>>
    suspend fun toggleFavorite(route: FavoriteRoute)
}