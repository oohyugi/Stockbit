package com.yogi.stockbit.watchlist.data.repository

import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.base.utils.fetchState
import com.yogi.stockbit.watchlist.data.model.toListBtc
import com.yogi.stockbit.watchlist.data.remote.ApiServices
import com.yogi.stockbit.watchlist.domain.WatchListRepository
import com.yogi.stockbit.watchlist.domain.model.BtcMdl

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

internal class WatchListRepositoryImpl(private val services: ApiServices) :
    WatchListRepository {
    override suspend fun getBtc(page: Int): ResultState<MutableList<BtcMdl>> {


        return fetchState {
            val res = services.getBtc(20, "USD", page = page)
            if (res.isSuccessful) {
                ResultState.Success(res.body()?.toListBtc())
            } else {
                ResultState.Error(res.message())
            }
        }
    }

}