package com.r.snell.flightsearch.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.r.snell.flightsearch.R
import com.r.snell.flightsearch.ui.component.FavoriteRouteList
import com.r.snell.flightsearch.ui.component.FlightList
import com.r.snell.flightsearch.viewmodel.FlightViewModel

@Composable
fun FlightSearchScreen(viewModel: FlightViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            label = { Text(stringResource(R.string.search_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        when {
            uiState.searchQuery.isBlank() -> {
                Text(stringResource(R.string.favorite_routes), style = MaterialTheme.typography.titleMedium)
                FavoriteRouteList(routes = uiState.favoriteRoutes)
            }
            else -> {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                } else if (uiState.flights.isNotEmpty()) {
                    Text(stringResource(R.string.available_flights), style = MaterialTheme.typography.titleMedium)
                    FlightList(
                        flights = uiState.flights,
                        onFavorite = viewModel::onFavoriteClicked,
                        isFavorited = { from, to -> uiState.isFavorited(from, to) }
                    )
                }
            }
        }
    }
}