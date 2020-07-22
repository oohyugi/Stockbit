package com.yogi.stockbit.features.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stockbit.features.home.R
import com.yogi.stockbit.features.home.domain.model.CryptoMdl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

class CryptoListAdapter :
    ListAdapter<CryptoMdl, RecyclerView.ViewHolder>(
        DiffUtilsCryptoAdapter()
    ) {

    private var adapterScope = CoroutineScope(Dispatchers.Default)


    private var listItem: MutableList<CryptoMdl> = mutableListOf()
    fun addAndSubmitList(list: List<CryptoMdl>?) {

        list?.let {
            listItem.addAll(list)
        }
        if (listItem.isNotEmpty()) submitList(listItem.toMutableList())


    }

    fun resetList() {
        listItem.clear()
        submitList(null)
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
            else -> (holder as ViewHolderLoader).bind(data)
        }


    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            currentList.size - 1 -> 2
            else -> 1
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val tvKet = itemView.findViewById<TextView>(R.id.tvKet)


        fun bind(
            data: CryptoMdl?
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


        fun bind(
            data: CryptoMdl?
        ) {


        }

    }


    class DiffUtilsCryptoAdapter : DiffUtil.ItemCallback<CryptoMdl>() {
        override fun areItemsTheSame(oldItem: CryptoMdl, newItem: CryptoMdl): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: CryptoMdl, newItem: CryptoMdl): Boolean {
            return oldItem == newItem

        }

    }

    class CryptoListAdapterListener(val clickListener: (item: CryptoMdl?) -> Unit) {
        fun onItemClickListener(item: CryptoMdl?) = clickListener(item)
    }


}