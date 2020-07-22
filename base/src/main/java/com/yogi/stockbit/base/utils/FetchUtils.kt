package com.yogi.stockbit.base.utils

import java.net.ConnectException

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

suspend fun <T : Any> fetchState(call: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        call.invoke()
    } catch (e: ConnectException) {

        ResultState.Error(handleConnectionError(e))
    } catch (e: Exception) {

        ResultState.Error(handleExceptionError(e))
    } catch (e: Throwable) {
        ResultState.Error(handleEThrowableError(e))

    }
}

fun handleConnectionError(e: ConnectException): String? = e.message
fun handleExceptionError(e: Exception): String? = e.message
fun handleEThrowableError(e: Throwable): String? = e.message
