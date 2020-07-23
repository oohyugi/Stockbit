package com.yogi.stockbit.login


import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */

val loginModule = module {


    viewModel {
        LoginViewModel()
    }




}

