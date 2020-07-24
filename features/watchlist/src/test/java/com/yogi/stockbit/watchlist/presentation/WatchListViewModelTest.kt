package com.yogi.stockbit.watchlist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.yogi.stockbit.base.utils.CoroutineRule
import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.base.utils.ViewState
import com.yogi.stockbit.watchlist.domain.WatchListUseCase
import com.yogi.stockbit.watchlist.domain.model.BtcMdl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by yogi on 7/24/2020
 * github: oohyugi
 */

@RunWith(MockitoJUnitRunner::class)
class WatchListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTest = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    internal lateinit var mockWatchListUseCase: WatchListUseCase
    private lateinit var vieModel: WatchListViewModel

    @Before
    fun setup() {
        vieModel = WatchListViewModel(mockWatchListUseCase)

    }

    @Test
    fun `execute getWatchListUseCase`() {
        // when
        vieModel.loadBtc(0)

        //then
        runBlocking {
            verify(mockWatchListUseCase).execute(0)
        }
    }


    @Test
    fun `verify state when GetWatchListhUseCase returns empty list`() {
        // given
        mockWatchListUseCase.stub {
            onBlocking { execute(0) } doReturn ResultState.Success(mutableListOf())
        }
        // when
        vieModel.loadBtc(0)

        // then
        vieModel.btcList.value shouldBeEqualTo ViewState(
            isLoading = false,
            isRefresh = false,
            isError = false,
            data = mutableListOf(),
            errorMessage = null
        )
    }

    @Test
    fun `verify state when GetWatchListhUseCase returns non empty list`() {

        val btcMdl = BtcMdl(name = "Bitcoin", title = "btc", price = "200")
        val list = mutableListOf(btcMdl)

        // given
        mockWatchListUseCase.stub {
            onBlocking { execute(0) } doReturn ResultState.Success(list)
        }
        // when
        vieModel.loadBtc(0)

        // then
        vieModel.btcList.value shouldBeEqualTo ViewState(
            isLoading = false,
            isRefresh = false,
            isError = false,
            data = list,
            errorMessage = null
        )
    }


}