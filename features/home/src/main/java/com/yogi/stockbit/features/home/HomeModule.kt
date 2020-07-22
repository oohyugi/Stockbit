package com.yogi.stockbit.features.home

import com.yogi.stockbit.features.home.domain.HomeRepository
import com.yogi.stockbit.features.home.domain.HomeUseCase
import com.yogi.stockbit.features.home.presentation.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

val homeModule = module {


    viewModel {
        HomeViewModel(get<HomeUseCase>())
    }

    single {
        provideHomeUseCase(get())
    }


}

internal fun provideHomeUseCase(repository: HomeRepository): HomeUseCase {
    return HomeUseCase(repository)
}