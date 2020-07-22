package com.yogi.stockbit.features.home

import com.yogi.stockbit.features.home.data.dataModule
import org.koin.core.context.loadKoinModules

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

object InitModule {
    private val loadModules by lazy {
        loadKoinModules(
            listOf(dataModule, homeModule)
        )
    }

    fun init() = loadModules
}