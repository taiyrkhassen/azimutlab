package com.example.azimutlab.mvp.presenters

import com.example.azimutlab.custom_errors.NoInternetException
import com.example.azimutlab.mvvm.repository.MainRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//inject through constructor
//@InjectViewState
//class MainPresenter @Inject constructor(private var mainRepo: MainRepositoryImpl) :
//    BasePresenter<MainActivityView>() {
//
//    //https://jsonplaceholder.typicode.com/posts/
//
//    fun getPosts() {
//        viewState.loadingData(true)
//        disposables.add(
//            mainRepo.getPosts()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    viewState.successGetData(it)
//                    viewState.loadingData(false)
//                }, {
//                    if (it is NoInternetException) {
//                        //how to get cash data?
//                        viewState.noInternetConnection()
//                    } else {
//                        viewState.loadingData(false)
//                        viewState.failedGetData(it.localizedMessage)
//                    }
//                })
//        )
//    }
//
//    fun dispose() {
//        disposables.dispose()
//    }
//
//}