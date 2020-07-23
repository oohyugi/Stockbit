package com.yogi.stockbit.features.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.stockbit.base.utils.ResultState
import com.yogi.stockbit.base.utils.ViewState
import com.yogi.stockbit.features.home.domain.HomeUseCase
import com.yogi.stockbit.features.home.domain.model.CryptoMdl
import kotlinx.coroutines.launch

internal class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {


    private val _crypto = MutableLiveData<ViewState<List<CryptoMdl>>>()
    val crypto: LiveData<ViewState<List<CryptoMdl>>> = _crypto


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
        loadCrypto(1)
    }


}