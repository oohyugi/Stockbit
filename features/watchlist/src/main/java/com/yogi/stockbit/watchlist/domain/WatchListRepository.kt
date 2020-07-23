package com.yogi.stockbit.watchlist.domain

import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.watchlist.domain.model.BtcMdl

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

internal interface WatchListRepository {
    suspend fun getBtc(page: Int): ResultState<MutableList<BtcMdl>>
}