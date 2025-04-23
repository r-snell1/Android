package com.r.snell.flightsearch.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import com.r.snell.flightsearch.domain.model.FlightWithAirportDetails
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder

@Composable
fun FlightList(
    flights: List<FlightWithAirportDetails>,
    onFavorite: (FavoriteRoute) -> Unit,
    isFavorited: (String, String) -> Boolean
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(flights, key = { it.id }) { flight ->
            val favorited = isFavorited(flight.fromAirport, flight.toAirport)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onFavorite(
                            FavoriteRoute().apply {
                                departureCode = flight.fromAirport
                                destinationCode = flight.toAirport
                            }
                        )
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("From: ${flight.fromAirportName} (${flight.fromAirport})")
                        Text("To: ${flight.toAirportName} (${flight.toAirport})")
                    }
                    Icon(
                        imageVector = if (favorited) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (favorited) "Unfavorite" else "Favorite"
                    )
                }
            }
        }
    }
}