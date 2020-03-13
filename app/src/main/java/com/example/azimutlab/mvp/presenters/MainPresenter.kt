package com.example.azimutlab.mvp.presenters

import com.example.azimutlab.AzimutApp
import com.example.azimutlab.custom_errors.NoInternetException
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.mvp.repository.MainRepositoryImpl
import com.example.azimutlab.mvp.view.interfaces.MainActivityView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter : BasePresenter<MainActivityView>() {

    @Inject
    lateinit var mainRepo: MainRepositoryImpl

    //https://jsonplaceholder.typicode.com/posts/1

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.getApplicationComponent()) //our dependency in service component
            .build()
            .inject(this)
    }

    fun getPosts() {
        viewState.loadingData(true)
        disposables.add(
            mainRepo.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.successGetData(it)
                    viewState.loadingData(false)
                }, {
                    if (it is NoInternetException) {
                        viewState.noInternetConnection()
                    } else {
                        viewState.loadingData(false)
                        viewState.failedGetData(it.localizedMessage)
                    }
                })
        )
    }

    fun dispose() {
        disposables.dispose()
    }

}