package com.r.snell.flightsearch.domain.model

data class FlightWithAirportDetails(
    val id: Int,
    val fromAirport: String,
    val toAirport: String,
    val fromAirportName: String,
    val toAirportName: String
)