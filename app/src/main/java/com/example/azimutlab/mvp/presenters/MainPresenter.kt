package com.example.azimutlab.mvp.presenters

import com.example.azimutlab.mvp.repository.MainRepository
import com.example.azimutlab.mvp.view.interfaces.MainActivityView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(): BasePresenter<MainActivityView>() {
    init{

    }
    @Inject lateinit var mainRepo: MainRepository


}