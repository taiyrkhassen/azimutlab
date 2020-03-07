package com.example.azimutlab.mvp.presenters

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.azimutlab.AzimutApp
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<T: MvpView> : MvpPresenter<T>() {

    var disposables = CompositeDisposable()


    private val cx = AzimutApp.appComponent.getContext()

    fun isConnectedToInternet():Boolean{
        val connectivityManager =
            cx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork != null
        } else {
            val netInfo = connectivityManager.activeNetworkInfo
            netInfo != null && netInfo.isConnectedOrConnecting
        }
    }

    fun destoyDispose() {
        disposables.dispose()
    }


}