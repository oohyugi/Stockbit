package com.yogi.stockbit.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.stockbit.base.utils.EndlessRecyclerViewScrollListener
import com.yogi.stockbit.features.home.InitModule
import com.yogi.stockbit.features.home.R
import com.yogi.stockbit.features.home.domain.model.CryptoMdl
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var listCrypto = mutableListOf<CryptoMdl>()
    private lateinit var mCryptoListAdapter: CryptoListAdapter
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InitModule.init()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerCrypto()
        setupListCrypto()
        initSwipeRefresh()

    }

    private fun initSwipeRefresh() {
        swipeRefresh?.setOnRefreshListener {
            homeViewModel.refreshCrypto()
            scrollListener.resetState()

        }
    }

    private fun setupListCrypto() {
        val mLayoutManager = LinearLayoutManager(context)
        mCryptoListAdapter = CryptoListAdapter()
        scrollListener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                homeViewModel.loadMore(page)

            }

        }
        rvCrypto?.apply {
            layoutManager = mLayoutManager
            adapter = mCryptoListAdapter

            addOnScrollListener(scrollListener)
        }
    }

    private fun observerCrypto() {
        homeViewModel.apply {
            crypto.observe(viewLifecycleOwner, Observer { result ->


                if (result.isRefresh) mCryptoListAdapter.resetList()
                progressBar.visibility = visibleView(result.isLoading && !swipeRefresh.isRefreshing)
                errorView.visibility = visibleView(result.isError)

                result.data?.let {
                    swipeRefresh?.isRefreshing = false
                    mCryptoListAdapter.addAndSubmitList(result.data)


                }

            })
            loadCrypto(1)
        }

    }


    private fun visibleView(loading: Boolean): Int {
        return if (loading) View.VISIBLE else View.GONE
    }


}