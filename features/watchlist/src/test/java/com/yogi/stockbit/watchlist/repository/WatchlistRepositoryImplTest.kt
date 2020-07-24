package com.yogi.stockbit.watchlist.repository

import com.nhaarman.mockitokotlin2.given
import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.watchlist.data.model.ResponseBtc
import com.yogi.stockbit.watchlist.data.model.toListBtc
import com.yogi.stockbit.watchlist.data.remote.ApiServices
import com.yogi.stockbit.watchlist.data.repository.WatchListRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Created by yogi on 7/24/2020
 * github: oohyugi
 */
@RunWith(MockitoJUnitRunner::class)
class WatchlistRepositoryImplTest {

    @Mock
    internal lateinit var mocApiServices: ApiServices

    private lateinit var repo: WatchListRepositoryImpl

    private var page = 0
    private var limit = 20

    @Before
    fun setup() {
        repo = WatchListRepositoryImpl(mocApiServices)

    }

    @Test
    fun `fetchBTC and map to List BtcMdl`() {
        runBlocking {
            given(
                mocApiServices.getBtc(limit, "USD", page = page)
            ).willReturn(Response.success(ResponseBtc()))

            val result = repo.getBtc(page)
            result shouldBeEqualTo ResultState.Success(ResponseBtc().toListBtc())

        }
    }

    @Test
    fun `fetchBTC return null if response is null`() {
        runBlocking {
            given(
                mocApiServices.getBtc(limit, "USD", page = page)
            ).willReturn(null)

            val result = repo.getBtc(page)
            result shouldBeEqualTo ResultState.Error(null)

        }
    }

    @Test
    fun `fetchBTC return error`() {
        runBlocking {
            given(
                repo.getBtc(page)
            ).willReturn(ResultState.Error(""))

            val result = repo.getBtc(page)
            result.javaClass shouldBeEqualTo ResultState.Error("null").javaClass

        }
    }

}