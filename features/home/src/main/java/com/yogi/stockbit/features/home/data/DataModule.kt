package com.yogi.stockbit.features.home.data

import com.yogi.stockbit.features.home.data.remote.ApiServices
import com.yogi.stockbit.features.home.data.repository.HomeRepositoryImpl
import com.yogi.stockbit.features.home.domain.HomeRepository
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */


val dataModule = module {

    fun provideHomeRepository(services: ApiServices): HomeRepositoryImpl {
        return HomeRepositoryImpl(services)
    }

    single<HomeRepository> {
        provideHomeRepository(get<Retrofit>().create(ApiServices::class.java))
    }

}