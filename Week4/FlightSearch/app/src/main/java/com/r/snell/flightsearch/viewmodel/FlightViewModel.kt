package com.r.snell.flightsearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r.snell.flightsearch.data.datastore.DataStoreManager
import com.r.snell.flightsearch.ui.state.FlightUiState
import com.r.snell.flightsearch.data.local.entity.Airport
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import com.r.snell.flightsearch.domain.model.FlightWithAirportDetails
import com.r.snell.flightsearch.domain.repository.FlightRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FlightViewModel : ViewModel(), KoinComponent {

    private val repository: FlightRepository by inject()
    private val dataStoreManager: DataStoreManager by inject()

    private val _uiState = MutableStateFlow(FlightUiState())
    val uiState: StateFlow<FlightUiState> = _uiState

    init {
        viewModelScope.launch {
            dataStoreManager.searchTextFlow.collectLatest {
                it?.let { query -> onSearchQueryChanged(query, saveToDataStore = false) }
            }

            repository.getFavoriteRoutes().collectLatest { favorites ->
                _uiState.update { it.copy(favoriteRoutes = favorites) }
            }
        }
    }

    fun onSearchQueryChanged(query: String, saveToDataStore: Boolean = true) {
        _uiState.update { it.copy(searchQuery = query) }

        if (saveToDataStore) {
            viewModelScope.launch { dataStoreManager.saveSearchQuery(query) }
        }

        viewModelScope.launch {
            repository.searchAirports(query).collect { suggestions ->
                _uiState.update { it.copy(suggestions = suggestions) }
            }
        }
    }

    fun onSuggestionSelected(airport: Airport) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            repository.getAllAirports().collect { allAirports ->
                val flights = generateMockFlights(airport, allAirports)
                _uiState.update {
                    it.copy(flights = flights, isLoading = false)
                }
            }
        }
    }

    fun onFavoriteClicked(route: FavoriteRoute) {
        viewModelScope.launch {
            repository.toggleFavorite(route)
        }
    }

    private fun generateMockFlights(from: Airport, allAirports: List<Airport>): List<FlightWithAirportDetails> {
        return allAirports
            .filter { it.iataCode != from.iataCode }
            .shuffled()
            .take(5)
            .mapIndexed { index, to ->
                FlightWithAirportDetails(
                    id = (from.id.toIntOrNull() ?: 0) * 1000 + (to.id.toIntOrNull() ?: 0) + index,
                    fromAirport = from.iataCode,
                    toAirport = to.iataCode,
                    fromAirportName = from.name,
                    toAirportName = to.name
                )
            }
    }
}