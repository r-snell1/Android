package com.r.snell.flightsearch.data.local.dao

import com.r.snell.flightsearch.data.local.entity.Airport
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.exceptions.RealmException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

interface FlightDao {

    // Autocomplete search for airports
    suspend fun searchAirports(query: String): Flow<List<Airport>> = withContext(Dispatchers.IO) {
        flow {
            val realm = getRealmInstance()  // Using a shared instance of Realm
            try {
                val airports = realm.query<Airport>("name CONTAINS $0 OR iataCode CONTAINS $0", query)
                    .find()
                emit(airports)
            } catch (e: RealmException) {
                e.printStackTrace()
            } finally {
                realm.close()
            }
        }
    }

    // Add or update a favorite route
    suspend fun insertFavorite(route: FavoriteRoute) = withContext(Dispatchers.IO) {
        val realm = getRealmInstance()
        try {
            realm.write {
                copyToRealm(route)
            }
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
    }

    // Get all favorite routes
    suspend fun getAllFavorites(): Flow<List<FavoriteRoute>> = withContext(Dispatchers.IO) {
        flow {
            val realm = getRealmInstance()
            try {
                val favorites = realm.query<FavoriteRoute>().find()
                emit(favorites)
            } catch (e: RealmException) {
                e.printStackTrace()
            } finally {
                realm.close()
            }
        }
    }

    // Remove a favorite route by departure and destination codes
    suspend fun removeFavorite(from: String, to: String) = withContext(Dispatchers.IO) {
        val realm = getRealmInstance()
        try {
            realm.write {
                val route = query<FavoriteRoute>("departureCode == $0 AND destinationCode == $1", from, to).first()
                route?.let { delete(it) }
            }
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
    }

    // Check if a favorite route exists
    suspend fun existsFavorite(from: String, to: String): Boolean = withContext(Dispatchers.IO) {
        val realm = getRealmInstance()
        return@withContext try {
            val exists = realm.query<FavoriteRoute>("departureCode == $0 AND destinationCode == $1", from, to).first().find()
            exists != null
        } catch (e: RealmException) {
            e.printStackTrace()
            false
        } finally {
            realm.close()
        }
    }

    // Get all airports
    suspend fun getAllAirports(): Flow<List<Airport>> = withContext(Dispatchers.IO) {
        flow {
            val realm = getRealmInstance()
            try {
                val airports = realm.query<Airport>().find()
                emit(airports)
            } catch (e: RealmException) {
                e.printStackTrace()
            } finally {
                realm.close()
            }
        }
    }

    // Get the shared Realm instance
    private fun getRealmInstance(): Realm {
        val config = RealmConfiguration.Builder(schema = setOf(Airport::class, FavoriteRoute::class))
            .name("flight_search.realm")
            .schemaVersion(1)
            .build()
        return Realm.open(config)
    }
}