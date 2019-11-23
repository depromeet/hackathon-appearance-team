package com.depromeet.hackthon7th

import android.app.Application
import io.realm.Realm

class RootApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}