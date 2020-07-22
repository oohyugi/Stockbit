package com.yogi.stockbit.base.utils


/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

sealed class ResultState<out T : Any?> {
    data class Success<out T : Any?>(val data: T?, val isLast: Boolean = false) : ResultState<T>()
    data class Error(val errorMessage: String?) : ResultState<Nothing>()
}