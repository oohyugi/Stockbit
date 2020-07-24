package com.yogi.stockbit.watchlist.data.remote

import com.yogi.stockbit.watchlist.data.model.ResponseBtc

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

interface ApiServices {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getBtc(
        @Query("limit") limit: Int,
        @Query("tsym") tsym: String,
        @Query("page") page: Int
    ): Response<ResponseBtc>
}