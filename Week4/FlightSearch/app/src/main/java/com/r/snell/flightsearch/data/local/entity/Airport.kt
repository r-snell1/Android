package com.r.snell.flightsearch.data.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Airport : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var iataCode: String = ""
}