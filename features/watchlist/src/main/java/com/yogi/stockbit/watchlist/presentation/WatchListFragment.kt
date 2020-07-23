package com.yogi.stockbit.watchlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stockbit.base.utils.EndlessRecyclerViewScrollListener
import com.yogi.stockbit.watchlist.InitModule
import com.yogi.stockbit.watchlist.R
import kotlinx.android.synthetic.main.fragment_watch_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class WatchListFragment : Fragment() {

    private val viewModel: WatchListViewModel by viewModel()
    private lateinit var mBtcListAdapter: BtcListAdapter
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InitModule.init()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watch_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerCrypto()
        setupListCrypto()
        initSwipeRefresh()
    }
    private fun initSwipeRefresh() {
        swipeRefresh?.setOnRefreshListener {
            viewModel.refreshCrypto()
            scrollListener.resetState()

        }
    }

    private fun setupListCrypto() {
        val mLayoutManager = LinearLayoutManager(context)
        mBtcListAdapter = BtcListAdapter()
        scrollListener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.loadMore(page)

            }

        }
        rvCrypto?.apply {
            layoutManager = mLayoutManager
            adapter = mBtcListAdapter

            addOnScrollListener(scrollListener)
        }
    }

    private fun observerCrypto() {
        viewModel.apply {
            btc.observe(viewLifecycleOwner, Observer { result ->


                if (result.isRefresh) mBtcListAdapter.resetList()
                progressBar.visibility = visibleView(result.isLoading && !swipeRefresh.isRefreshing)
                errorView.visibility = visibleView(result.isError)

                result.data?.let {
                    swipeRefresh?.isRefreshing = false
                    mBtcListAdapter.addAndSubmitList(result.data)


                }

            })
            loadCrypto(0)
        }

    }


    private fun visibleView(loading: Boolean): Int {
        return if (loading) View.VISIBLE else View.GONE
    }

}