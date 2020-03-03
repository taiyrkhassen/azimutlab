package com.example.azimutlab.mvp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.azimutlab.R
import com.example.azimutlab.mvp.models.PostModel
import com.example.azimutlab.mvp.view.interfaces.MainActivityView

class MainActivity : BaseActivity(), MainActivityView {
    override fun failedGetData(msg:String) {

    }

    override fun successGetData(list:List<PostModel>) {

    }

    override fun loadingData(loading:Boolean) {

    }

    override fun noInternetConnection() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
