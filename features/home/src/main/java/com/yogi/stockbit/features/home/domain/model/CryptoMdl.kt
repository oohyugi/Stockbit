package com.yogi.stockbit.features.home.domain.model

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

data class CryptoMdl(
    val id: String? = null,
    val title: String? = null,
    val name: String? = null,
    val price: String? = null,
    val changePriceHourDisplay: String? = null,
    val changePriceHour: Double? = 0.0,
    val changePercentHour: String? = null

)