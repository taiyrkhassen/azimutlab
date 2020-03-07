package com.example.azimutlab.mvp.presenters

import android.content.Context
import android.content.SharedPreferences
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.Constants
import com.example.azimutlab.helpers.PreferenceHelper
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.mvp.models.PostModel
import com.example.azimutlab.mvp.repository.MainRepository
import com.example.azimutlab.mvp.view.interfaces.MainActivityView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject
import com.google.gson.Gson

@InjectViewState
class MainPresenter @Inject constructor(var mainRepo: MainRepository) : BasePresenter<MainActivityView>() {

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.getApplicationComponent()) //our dependency in service component
            .build()
            .inject(this)
    }
    // если вот срочно понадобится контекст то вытаскивать так?
    //var context = AzimutApp.getApplicationComponent().getContext()
    @Inject
    lateinit var mPrefs : SharedPreferences

    fun getPosts() {

        if (!isConnectedToInternet()) {
            viewState.noInternetConnection()
            return
        }

        viewState.loadingData(true)
        //добавление в кэш делать в фооновом потоке тоже
        disposables.add(
            mainRepo.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnNext {
                    addToCash(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.successGetData(it)
                    viewState.loadingData(false)
                }, {
                    viewState.loadingData(false)
                    viewState.failedGetData(it.localizedMessage)
                })
        )
    }

    private fun addToCash(list: List<PostModel>) {
        val appSharedPrefs2 = mPrefs
        val prefsEditor = appSharedPrefs2.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        //remove old data
        prefsEditor.remove(Constants.LIST_POSTS_CASHE).apply()
        //add new data
        prefsEditor.putString(Constants.LIST_POSTS_CASHE, json)
        prefsEditor.commit()
    }

}