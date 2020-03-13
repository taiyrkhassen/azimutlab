package com.example.azimutlab.mvp.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.Constants
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.mvp.models.PostModel
import io.reactivex.Observable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class MainRepositoryImpl(private var apiService: ApiService) :
    MainRepository {

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.appComponent)
            .build()
            .inject(this)
    }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("CheckResult")
    override fun getPosts(): Observable<List<PostModel>> {
       return apiService.getPostsJson(1)
            .flatMap {
                if (it.isSuccessful) {
                    addToCash(it.body()?: emptyList())
                    Observable.just(it.body()?: emptyList())
                } else {
                    Observable.just(getPostsFromCache())
                }
            }
    }

    private fun getPostsFromCache(): List<PostModel>? {
        val appSharedPrefs = sharedPreferences
        val gson = Gson()
        val json = appSharedPrefs.getString(Constants.LIST_POSTS_CASHE, "")
        val type = object : TypeToken<List<PostModel>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun addToCash(list: List<PostModel>) {
        val appSharedPrefs2 = sharedPreferences
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