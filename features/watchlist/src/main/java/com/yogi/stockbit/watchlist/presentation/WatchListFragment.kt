package com.yogi.stockbit.watchlist.presentation

import android.os.Bundle
import android.util.Log
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
            viewModel.refreshBtc()


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
            btcList.observe(viewLifecycleOwner, Observer { result ->


                progressBar.visibility = visibleView(result.isLoading && !swipeRefresh.isRefreshing)

                showError(result.isError, result.errorMessage)
                swipeRefresh?.isRefreshing = result.isRefresh

                Log.wtf("isRefresh", result.isRefresh.toString())
                result.dataRefresh?.let {
                    scrollListener.resetState()
                    mBtcListAdapter.refreshData(it)
                    rvCrypto?.smoothScrollToPosition(0)
                }
                result.data?.let {
                    mBtcListAdapter.addAndSubmitList(result.data)
                }


            })
            loadBtc(0)
        }

    }

    private fun showError(isError: Boolean, errorMessage: String?) {
        errorView?.visibility = visibleView(isError)
        tvErrorMessage?.text = errorMessage ?: "Data not found"
    }


    private fun visibleView(loading: Boolean): Int {

        return if (loading) View.VISIBLE else View.GONE
    }

}