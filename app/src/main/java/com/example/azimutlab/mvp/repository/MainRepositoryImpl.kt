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
import io.reactivex.Single
import javax.inject.Inject

//shared pref through constructor injetction
class MainRepositoryImpl(private val apiService: ApiService, private val sharedPreferences: SharedPreferences) :
    MainRepository {


    @SuppressLint("CheckResult")
    override fun getPosts(): Single<List<PostModel>> {
       return apiService.getPostsJson(1)
            .flatMap {
                if (it.isSuccessful) {
                    addToCash(it.body()?: emptyList())
                    Single.just(it.body()?: emptyList())
                } else {
                    Single.just(getPostsFromCache())
                }
            }
    }

    private fun getPostsFromCache(): List<PostModel>? {
        val gson = Gson()
        val json = sharedPreferences.getString(Constants.LIST_POSTS_CACHE, "")
        val type = object : TypeToken<List<PostModel>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun addToCash(list: List<PostModel>) {
        val prefsEditor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        //remove old data
        prefsEditor.remove(Constants.LIST_POSTS_CACHE).apply()
        //add new data
        prefsEditor.putString(Constants.LIST_POSTS_CACHE, json)
        prefsEditor.commit()
    }
}