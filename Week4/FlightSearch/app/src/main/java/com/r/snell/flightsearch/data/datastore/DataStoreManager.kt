package com.r.snell.flightsearch.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "flight_preferences"

private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreManager(private val context: Context) {
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    val darkThemeFlow: Flow<Boolean> = context.dataStore.data
        .map { it[DARK_MODE_KEY] ?: false }

    suspend fun saveDarkTheme(isDark: Boolean) {
        context.dataStore.edit { prefs -> prefs[DARK_MODE_KEY] = isDark }
    }

    companion object {
        private val SEARCH_QUERY_KEY = stringPreferencesKey("search_query")
    }

    val searchTextFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[SEARCH_QUERY_KEY]
    }

    suspend fun saveSearchQuery(query: String) {
        context.dataStore.edit { preferences ->
            preferences[SEARCH_QUERY_KEY] = query
        }
    }
}