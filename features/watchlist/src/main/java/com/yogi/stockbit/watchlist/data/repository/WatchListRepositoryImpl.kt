package com.yogi.stockbit.watchlist.data.repository

import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.base.utils.fetchState
import com.yogi.stockbit.watchlist.data.model.toListCrypto
import com.yogi.stockbit.watchlist.data.remote.ApiServices
import com.yogi.stockbit.watchlist.domain.WatchListRepository
import com.yogi.stockbit.watchlist.domain.model.CryptoMdl

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

internal class WatchListRepositoryImpl(private val services: ApiServices) :
    WatchListRepository {
    override suspend fun getCrypto(page: Int): ResultState<MutableList<CryptoMdl>> {


        return fetchState {
            val res = services.getCrypto(20, "IDR", page = page)
            if (res.isSuccessful) {
                ResultState.Success(res.body()?.toListCrypto())
            } else {
                ResultState.Error(res.message())
            }
        }
    }

}