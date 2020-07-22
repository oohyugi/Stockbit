package com.yogi.stockbit.base.extension

import java.text.DecimalFormat

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

fun Double.roundOffDecimal(): Double? {
    val df = DecimalFormat("#.##")

    return df.format(this).toDouble()
}