package com.r.snell.flightsearch.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import com.r.snell.flightsearch.data.local.entity.Airport

@Composable
fun FavoriteRouteList(routes: List<FavoriteRoute>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        routes.forEach { route ->
            Column(modifier = Modifier.padding(vertical = 6.dp)) {
                Text("From: ${route.departureCode}")
                Text("To: ${route.destinationCode}")
            }
        }
    }
}