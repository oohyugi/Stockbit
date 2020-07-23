package com.yogi.stockbit.login


import org.koin.core.context.loadKoinModules

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

object InitModule {
    private val loadModules by lazy {
        loadKoinModules(
            listOf(loginModule)
        )
    }

    fun init() = loadModules
}