package com.yogi.stockbit.watchlist.data

import com.yogi.stockbit.watchlist.data.remote.ApiServices
import com.yogi.stockbit.watchlist.data.repository.WatchListRepositoryImpl
import com.yogi.stockbit.watchlist.domain.WatchListRepository
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */


val dataModule = module {

    fun provideHomeRepository(services: ApiServices): WatchListRepositoryImpl {
        return WatchListRepositoryImpl(services)
    }

    single<WatchListRepository> {
        provideHomeRepository(get<Retrofit>().create(ApiServices::class.java))
    }

}