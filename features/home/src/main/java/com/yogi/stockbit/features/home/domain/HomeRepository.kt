package com.yogi.stockbit.features.home.domain

import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.features.home.domain.model.CryptoMdl

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

internal interface HomeRepository {
    suspend fun getCrypto(page: Int): ResultState<MutableList<CryptoMdl>>
}