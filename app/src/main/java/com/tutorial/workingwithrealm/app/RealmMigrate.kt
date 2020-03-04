package com.tutorial.workingwithrealm.app

import android.util.Log
import io.realm.DynamicRealm
import io.realm.RealmMigration

class RealmMigrate : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema
        Log.d("OLD VERSION", "" + oldVersion)

        if (oldVersion == 1L) {
            //this is how we migrate in realm
            // schema.create("NewSchema")
            // schema.create("TVItem")
        }
    }
}