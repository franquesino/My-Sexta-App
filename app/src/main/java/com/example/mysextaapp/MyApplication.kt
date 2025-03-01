package com.example.mysextaapp


import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = RealmConfiguration.Builder(schema = setOf(Infraccion::class))
            .name("infracciones.realm")
            .schemaVersion(1)
            .build()
        Realm.open(config)
    }
}
