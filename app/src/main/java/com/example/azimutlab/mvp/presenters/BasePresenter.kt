package com.example.azimutlab.mvp.presenters

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    var disposables = CompositeDisposable()

    fun destroyDispose() {
        disposables.dispose()
    }


}