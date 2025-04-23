package com.r.snell.flightsearch.di

import com.r.snell.flightsearch.data.repository.FlightRepositoryImpl
import com.r.snell.flightsearch.domain.repository.FlightRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import com.r.snell.flightsearch.data.local.entity.Airport
import com.r.snell.flightsearch.data.local.entity.FavoriteRoute
import org.koin.dsl.module

val appModule = module {
    single<Realm> { getRealmInstance() }
    single<FlightRepository> { FlightRepositoryImpl(get()) }
}

private fun getRealmInstance(): Realm {
    val config = RealmConfiguration.Builder(
        schema = setOf(Airport::class, FavoriteRoute::class)
    )
        .name("flight_search.realm")
        .schemaVersion(1)
        .build()
    return Realm.open(config)
}