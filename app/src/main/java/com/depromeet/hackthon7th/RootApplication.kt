package com.depromeet.hackthon7th

import android.app.Application
import android.content.Context
import io.realm.Realm

class RootApplication: Application() {

    companion object {

        private var APPLICATION_CONTEXT: Context? = null

        @JvmStatic
        fun getContext(): Context {
            return APPLICATION_CONTEXT!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}