package com.yogi.stockbit.watchlist

import com.yogi.stockbit.watchlist.domain.WatchListUseCase
import com.yogi.stockbit.watchlist.presentation.WatchListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 23/07/20.
 * Github : https://github.com/oohyugi
 */

val watchListModule = module {


    viewModel {
        WatchListViewModel(get())
    }

    single {
       WatchListUseCase(get())
    }
}