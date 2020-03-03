package com.example.azimutlab.mvp.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.PreferenceHelper
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.mvp.models.PostModel
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainRepository {

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.getApplicationComponent())
            .build()
            .inject(this)
    }

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var mContext: Context

    @SuppressLint("CheckResult")
    fun getPosts(): Observable<List<PostModel>> {
        val list: ArrayList<PostModel> = arrayListOf()
        getPostsFromApi().subscribe({
            if (it.code() == 200 || it.code() == 201) {
                list.addAll(it.body()!!)
            } else {
                list.addAll(getPostsFromCashe()?:ArrayList())
            }
        }, {})
        return Observable.just(list)
    }

    //проверить при забирании на нулл
    fun getPostsFromCashe(): ArrayList<PostModel>? {
        val appSharedPrefs = PreferenceHelper.defaultPrefs(mContext)
        val gson = Gson()
        val json = appSharedPrefs.getString("MyObject", "")
        val type = object : TypeToken<List<PostModel>>() {}.type
        return gson.fromJson(json, type) as ArrayList<PostModel>
    }

    fun getPostsFromApi(): Observable<Response<List<PostModel>>> {
        return apiService.getPostsJson(1)
    }
}