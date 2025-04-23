package com.r.snell.flightsearch.data.migration

import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import io.realm.kotlin.Realm
import com.r.snell.flightsearch.data.local.entity.Airport
import java.io.File
import java.io.FileOutputStream
import androidx.core.content.edit

object DbMigrator {

    private const val PREFS_NAME = "migration_prefs"
    private const val KEY_MIGRATED = "db_migrated"

    fun migrateFromSQLiteToRealm(context: Context, realm: Realm) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if (prefs.getBoolean(KEY_MIGRATED, false)) return

        val dbPath = File(context.filesDir, "flight_search.db")

        if (!dbPath.exists()) {
            context.assets.open("flight_search.db").use { input ->
                FileOutputStream(dbPath).use { output ->
                    input.copyTo(output)
                }
            }
        }

        val sqlite = SQLiteDatabase.openDatabase(
            dbPath.path,
            null,
            SQLiteDatabase.OPEN_READONLY
        )

        val cursor = sqlite.rawQuery("SELECT * FROM airport", null)
        realm.writeBlocking {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val iata = cursor.getString(cursor.getColumnIndexOrThrow("iataCode"))

                copyToRealm(Airport().apply {
                    this.id = id
                    this.name = name
                    this.iataCode = iata
                })
            }
        }

        cursor.close()
        sqlite.close()

        prefs.edit { putBoolean(KEY_MIGRATED, true) }
    }
}