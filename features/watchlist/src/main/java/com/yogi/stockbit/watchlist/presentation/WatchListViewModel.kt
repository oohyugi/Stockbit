package com.yogi.stockbit.watchlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.base.utils.ViewState
import com.yogi.stockbit.watchlist.domain.WatchListUseCase
import com.yogi.stockbit.watchlist.domain.model.BtcMdl
import kotlinx.coroutines.launch

internal class WatchListViewModel(private val useCase: WatchListUseCase) : ViewModel() {

    private val _btcList = MutableLiveData<ViewState<List<BtcMdl>>>()
    val btcList: LiveData<ViewState<List<BtcMdl>>> = _btcList


    fun loadBtc(page: Int, isRefresh: Boolean = false) {

        if (isRefresh) {
            _btcList.value = ViewState(isRefresh = true)

        }
        viewModelScope.launch {
            val request =
                useCase.execute(page)

            when (request) {
                is ResultState.Success -> {
                    _btcList.value =
                        ViewState(
                            isLoading = false, isError = false, isRefresh = false,
                            data = if (isRefresh) null else request.data,
                            dataRefresh = if (isRefresh) request.data else null
                        )
                }
                is ResultState.Error -> {
                    _btcList.value = ViewState(
                        isLoading = false,
                        isError = true,
                        isRefresh = false,
                        errorMessage = request.errorMessage
                    )

                }
            }


        }
    }

    fun loadMore(page: Int) {

        loadBtc(page)
    }

    fun refreshBtc() {

        loadBtc(0, true)
    }


}
