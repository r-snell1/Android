package com.r.snell.flightsearch.data.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class FavoriteRoute : RealmObject {
    @PrimaryKey
    var id: String = ""
    var departureCode: String = ""
    var destinationCode: String = ""
}
