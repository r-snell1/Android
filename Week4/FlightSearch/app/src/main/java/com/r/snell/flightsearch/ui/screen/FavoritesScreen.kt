package com.r.snell.flightsearch.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.r.snell.flightsearch.ui.component.FavoriteRouteList
import com.r.snell.flightsearch.viewmodel.FlightViewModel
import com.r.snell.flightsearch.R

@Composable
fun FavoritesScreen(viewModel: FlightViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(stringResource(R.string.favorite_routes), style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))

        if (uiState.favoriteRoutes.isNotEmpty()) {
            FavoriteRouteList(routes = uiState.favoriteRoutes)
        } else {
            Text(stringResource(R.string.no_favorites))
        }
    }
}