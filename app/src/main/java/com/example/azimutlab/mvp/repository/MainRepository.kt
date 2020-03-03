package com.example.azimutlab.mvp.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.Constants
import com.example.azimutlab.PreferenceHelper
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.mvp.models.PostModel
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainRepository(context: Context, private var apiService: ApiService) {
    var mContext:Context = context
    //    init {
//        DaggerServiceComponent.builder()
//            .appComponent(AzimutApp.getApplicationComponent())
//            .build()
//            .inject(this)
//    }
//
//    @Inject
//    lateinit var apiService: ApiService
//
//    @Inject
//    lateinit var mContext: Context

    @SuppressLint("CheckResult")
    fun getPosts(): Observable<List<PostModel>> {
        val list: ArrayList<PostModel> = arrayListOf()
        getPostsFromApi().doOnNext{
            list.addAll(it)
        }.doOnError{
            list.addAll(getPostsFromCashe()!!)
        }
        return Observable.just(list)
    }

    //проверить при забирании на нулл
    private fun getPostsFromCashe(): ArrayList<PostModel>? {
        val appSharedPrefs = PreferenceHelper.defaultPrefs(mContext)
        val gson = Gson()
        val json = appSharedPrefs.getString(Constants.LIST_POSTS_CASHE, "")
        val type = object : TypeToken<List<PostModel>>() {}.type
        return gson.fromJson(json, type) as ArrayList<PostModel>
    }

    private fun getPostsFromApi(): Observable<List<PostModel>> {
        return apiService.getPostsJson(1)
    }

}