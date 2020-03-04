package com.tutorial.workingwithrealm.app

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class AppDelegate:Application() {
    companion object {
        private val TAG = AppDelegate::class.java.simpleName
        @get:Synchronized var instance: AppDelegate? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initRealm(this)
    }

    fun initRealm(context: Context){
        Realm.init(context)
        val realmConfig = RealmConfiguration.Builder()
            .name("realm_tutorial_db.realm")
            .schemaVersion(1)
            .migration(RealmMigrate())
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}