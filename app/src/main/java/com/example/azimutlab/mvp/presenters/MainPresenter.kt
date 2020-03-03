package com.example.azimutlab.mvp.presenters

import android.content.Context
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.Constants
import com.example.azimutlab.PreferenceHelper
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
class MainPresenter @Inject constructor() : BasePresenter<MainActivityView>() {

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.getApplicationComponent())
            .build()
            .inject(this)
    }

    @Inject
    lateinit var mainRepo: MainRepository
    @Inject
    lateinit var mContext: Context

    fun getPosts() {
        viewState.loadingData(true)
        disposables.add(
            mainRepo.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.loadingData(false)
                    viewState.successGetData(it)
                    addToCash(it)
                }, {
                    viewState.failedGetData(it.localizedMessage)
                })
        )
    }

    fun addToCash(list: List<PostModel>) {
        val appSharedPrefs2 = PreferenceHelper.defaultPrefs(context = mContext)
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