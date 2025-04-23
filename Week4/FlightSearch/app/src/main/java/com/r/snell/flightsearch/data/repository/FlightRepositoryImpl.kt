package com.r.snell.flightsearch.data.repository

import com.r.snell.flightsearch.data.local.dao.FlightDao
import com.r.snell.flightsearch.data.local.entity.Airport
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import com.r.snell.flightsearch.domain.repository.FlightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first

class FlightRepositoryImpl(
    private val flightDao: FlightDao
) : FlightRepository {

    override fun searchAirports(query: String): Flow<List<Airport>> = flow {
        emit(flightDao.searchAirports("%${query.trim()}%").first())
    }

    override fun getFavoriteRoutes(): Flow<List<FavoriteRoute>> = flow {
        emit(flightDao.getAllFavorites().first())
    }

    override fun getAllAirports(): Flow<List<Airport>> = flow {
        emit(flightDao.getAllAirports().first())
    }

    override suspend fun toggleFavorite(route: FavoriteRoute) {
        val isAlreadyFavorite = flightDao.existsFavorite(route.departureCode, route.destinationCode)
        if (isAlreadyFavorite) {
            flightDao.removeFavorite(route.departureCode, route.destinationCode)
        } else {
            flightDao.insertFavorite(route)
        }
    }
}