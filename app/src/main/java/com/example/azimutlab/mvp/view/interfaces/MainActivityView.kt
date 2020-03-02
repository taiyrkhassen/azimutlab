package com.example.azimutlab.mvp.view.interfaces

interface MainActivityView: BaseView {
    fun failedGetData()
    fun successGetData()
    fun loadingData()
}