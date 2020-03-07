package com.example.azimutlab.mvp.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log.d
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.Constants
import com.example.azimutlab.helpers.PreferenceHelper
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.mvp.models.PostModel
import io.reactivex.Observable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import javax.inject.Inject

class MainRepository(private var apiService: ApiService) :
    MainRepositoryInterface {

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.appComponent)
            .build()
            .inject(this)
    }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    //back pressure strategy есть только на Flowable
    @SuppressLint("CheckResult")
    override fun getPosts(): Observable<List<PostModel>> {
        val list: ArrayList<PostModel> = arrayListOf()
        apiService.getPostsJson(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.addAll(it)
            }, {
                list.addAll(getPostsFromCache() ?: ArrayList())
            })
        d("check_list", list.size.toString())
        return Observable.just(list)
    }

    //проверить при забирании на нулл
    private fun getPostsFromCache(): ArrayList<PostModel>? {
        val appSharedPrefs = sharedPreferences
        val gson = Gson()
        val json = appSharedPrefs.getString(Constants.LIST_POSTS_CASHE, "")
        val type = object : TypeToken<List<PostModel>>() {}.type
        return gson.fromJson(json, type) as ArrayList<PostModel>?
    }
}