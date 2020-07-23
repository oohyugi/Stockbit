package com.yogi.stockbit.watchlist.data.remote

import com.yogi.stockbit.watchlist.data.model.ResponseCrypto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

interface ApiServices {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getCrypto(
        @Query("limit") limit: Int,
        @Query("tsym") tsym: String,
        @Query("page") page: Int
    ): Response<ResponseCrypto>
}