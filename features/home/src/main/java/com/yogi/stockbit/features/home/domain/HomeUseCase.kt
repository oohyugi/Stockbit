package com.yogi.stockbit.features.home.domain

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

internal class HomeUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(page: Int) = homeRepository.getCrypto(page)

}