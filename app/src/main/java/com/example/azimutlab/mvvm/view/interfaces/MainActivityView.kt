package com.example.azimutlab.mvvm.view.interfaces

import com.example.azimutlab.mvvm.models.PostModel

interface MainActivityView: BaseView {
    fun failedGetData(msg:String)
    fun successGetData(list:List<PostModel>)
    fun loadingData(loading:Boolean)
}