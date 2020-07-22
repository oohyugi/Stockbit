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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    private var mlist: MutableList<CryptoMdl> = mutableListOf()
    private val _crypto = MutableLiveData<ViewState<List<CryptoMdl>>>()
    val crypto: LiveData<ViewState<List<CryptoMdl>>> = _crypto
    var mPage = 1

    fun loadCrypto(page: Int) {

        Log.wtf("page ", "$page")
        viewModelScope.launch {
            val request = withContext(Dispatchers.IO) {
                useCase.execute(page)
            }
            when (request) {
                is ResultState.Success -> {
                    request.data?.let { it1 -> mlist.addAll(it1) }
                    _crypto.value =
                        ViewState(isLoading = false, isError = false, data = mlist)
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
        mPage++
        loadCrypto(mPage)
    }


}