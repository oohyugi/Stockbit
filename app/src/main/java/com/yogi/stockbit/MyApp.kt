package com.yogi.stockbit

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            androidFileProperties()
            modules(
                listOf(
                    appModule
                )
            )
        }
    }
}