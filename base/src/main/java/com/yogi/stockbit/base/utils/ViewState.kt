package com.yogi.stockbit.base.utils

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

data class ViewState<T>(
    val isLoading: Boolean = true,
    val isRefresh: Boolean = false,
    val isError: Boolean = false,
    val data: T? = null,
    val errorMessage: String? = null
)