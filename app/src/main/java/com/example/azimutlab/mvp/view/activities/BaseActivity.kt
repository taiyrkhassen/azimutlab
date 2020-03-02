package com.example.azimutlab.mvp.view.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun isOnline():Boolean{
        val cm = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)!!
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }



}