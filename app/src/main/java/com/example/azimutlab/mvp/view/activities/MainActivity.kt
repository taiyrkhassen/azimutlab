package com.example.azimutlab.mvp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.azimutlab.R
import com.example.azimutlab.mvp.view.interfaces.MainActivityView

class MainActivity : BaseActivity(), MainActivityView {
    override fun failedGetData() {

    }

    override fun successGetData() {

    }

    override fun loadingData() {

    }

    override fun noInternetConnection() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
