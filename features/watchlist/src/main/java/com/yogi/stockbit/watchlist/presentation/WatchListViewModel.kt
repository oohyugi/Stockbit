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

    private val _crypto = MutableLiveData<ViewState<List<BtcMdl>>>()
    val btc: LiveData<ViewState<List<BtcMdl>>> = _crypto


    fun loadCrypto(page: Int) {
        viewModelScope.launch {
            val request =
                useCase.execute(page)

            when (request) {
                is ResultState.Success -> {
                    _crypto.value =
                        ViewState(isLoading = false, isError = false, data = request.data)
                }
                is ResultState.Error -> {
                    _crypto.value = ViewState(
                        isLoading = false,
                        isError = true,
                        errorMessage = request.errorMessage
                    )

                }
            }


        }
    }

    fun loadMore(page: Int) {

        loadCrypto(page)
    }

    fun refreshCrypto() {
        _crypto.value = ViewState(isRefresh = true)
        loadCrypto(0)
    }


}
