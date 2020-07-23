package com.yogi.stockbit.watchlist.domain

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

internal class WatchListUseCase(private val homeRepository: WatchListRepository) {
    suspend fun execute(page: Int) = homeRepository.getCrypto(page)

}