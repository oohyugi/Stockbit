package com.yogi.stockbit.watchlist

import com.yogi.stockbit.watchlist.data.dataModule
import org.koin.core.context.loadKoinModules

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

object InitModule {
    private val loadModules by lazy {
        loadKoinModules(
            listOf(dataModule, watchListModule)
        )
    }

    fun init() = loadModules
}