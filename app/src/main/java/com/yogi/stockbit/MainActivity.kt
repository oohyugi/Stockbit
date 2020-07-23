package com.yogi.stockbit

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = nav_host_fragment.findNavController()
        navController.addOnDestinationChangedListener { controller, destination, arguments ->


            if (destination.displayName == getString(R.string.loginFragment)){
                nav_view?.visibility = View.GONE
            }else{
                nav_view?.visibility = View.VISIBLE
            }
        }
//        nav_view.setupWithNavController(navController)
    }
}