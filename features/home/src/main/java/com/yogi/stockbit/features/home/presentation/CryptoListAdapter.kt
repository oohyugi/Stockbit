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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

class CryptoListAdapter(val listener: CryptoListAdapterListener) :
    ListAdapter<CryptoMdl, CryptoListAdapter.ViewHolder>(
        DiffUtilsCryptoAdapter()
    ) {

    private var adapterScope = CoroutineScope(Dispatchers.Default)


    private var listItem: MutableList<CryptoMdl> = mutableListOf()
    fun addAndSubmitList(list: List<CryptoMdl>?) {
        adapterScope.launch {
            list?.let {
                listItem.addAll(list)
            }

            withContext(Dispatchers.Main) {
                submitList(listItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_crypto, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.bind(data, listener)


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val tvKet = itemView.findViewById<TextView>(R.id.tvKet)


        fun bind(
            data: CryptoMdl?,
            listener: CryptoListAdapterListener
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


            itemView.setOnClickListener {
                listener.onItemClickListener(data)
            }

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