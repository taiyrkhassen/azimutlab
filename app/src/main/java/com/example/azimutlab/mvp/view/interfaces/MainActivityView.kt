package com.example.azimutlab.mvp.view.interfaces

import com.example.azimutlab.mvp.models.PostModel

interface MainActivityView: BaseView {
    fun failedGetData(msg:String)
    fun successGetData(list:List<PostModel>)
    fun loadingData(loading:Boolean)
}