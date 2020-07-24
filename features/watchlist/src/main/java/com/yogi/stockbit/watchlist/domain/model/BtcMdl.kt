package com.yogi.stockbit.watchlist.domain.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */
@Parcelize
data class BtcMdl(
    val id: String? = null,
    val title: String? = null,
    val name: String? = null,
    val price: String? = null,
    val changePriceHourDisplay: String? = null,
    val changePriceHour: Double? = 0.0,
    val changePercentHour: String? = null

) : Parcelable {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<BtcMdl>() {
            override fun areItemsTheSame(
                oldItem: BtcMdl,
                newItem: BtcMdl
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: BtcMdl,
                newItem: BtcMdl
            ): Boolean = oldItem == newItem
        }
    }
}