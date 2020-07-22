package com.yogi.stockbit.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by yogi on 7/22/2020
 * github: oohyugi
 */


class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }
}