package com.r.snell.flightsearch

import android.app.Application
import com.r.snell.flightsearch.data.migration.DbMigrator
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.context.startKoin
import com.r.snell.flightsearch.di.appModule
import org.koin.android.ext.koin.androidContext

class FlightSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FlightSearchApp)
            modules(appModule)
        }

        val config = RealmConfiguration.Builder(
            schema = setOf(
                com.r.snell.flightsearch.data.local.entity.Airport::class,
                com.r.snell.flightsearch.data.local.entity.FavoriteRoute::class
            )
        ).name("flight_search.realm").build()

        val realm = Realm.open(config)
        DbMigrator.migrateFromSQLiteToRealm(this, realm)

        realm.close()
    }
}