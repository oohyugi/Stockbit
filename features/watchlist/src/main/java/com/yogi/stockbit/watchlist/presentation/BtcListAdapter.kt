package com.yogi.stockbit.watchlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stockbit.watchlist.R
import com.yogi.stockbit.watchlist.domain.model.BtcMdl


/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

class BtcListAdapter :
    ListAdapter<BtcMdl, RecyclerView.ViewHolder>(
        BtcMdl.DiffCallback
    ) {

  private var isLoading = true

    private var listItem: MutableList<BtcMdl> = mutableListOf()
    fun addAndSubmitList(list: List<BtcMdl>?) {

        list?.let {
            listItem.addAll(list)
            submitList(listItem.toMutableList())
        }


    }

    fun refreshData(list: List<BtcMdl>) {

        if (listItem.isNotEmpty()) {
            for (i in list.indices) {
                listItem[i] = list[i]
            }
        } else {
            listItem.addAll(list)
        }

        submitList(listItem.toMutableList())


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View
        return when (viewType) {
            1 -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_crypto, parent, false)
                ViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.bottom_loading, parent, false)
                ViewHolderLoader(view)
            }
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        when (holder.itemViewType) {
            1 -> (holder as ViewHolder).bind(data)
            else -> (holder as ViewHolderLoader).bind()
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size - 1 && isLoading) {
            2
        } else {
            1
        }

    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val tvKet = itemView.findViewById<TextView>(R.id.tvKet)


        fun bind(
            data: BtcMdl?
        ) {


            tvTitle.text = data?.title
            tvName.text = data?.name
            tvPrice.text = data?.price.toString()
            tvKet.text =
                "${data?.changePriceHourDisplay?.split(" ")?.get(1)} (${data?.changePercentHour}%)"
            data?.changePriceHour?.let {
                if (it < 0.0) {
                    tvKet.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            android.R.color.holo_red_light
                        )
                    )
                } else {
                    tvKet.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            android.R.color.holo_green_light
                        )
                    )
                }
            }


        }

    }

    inner class ViewHolderLoader(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {}

    }



}