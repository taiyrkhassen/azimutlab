package com.example.azimutlab.mvvm.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.azimutlab.Constants
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.mvvm.models.PostModel
import io.reactivex.Observable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//shared pref through constructor injetction
class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : MainRepository {


    @SuppressLint("CheckResult")
    override fun getPosts(): Observable<List<PostModel>> {
        return apiService.getPostsJson()
            .flatMap {
                if (it.isSuccessful) {
                    addToCash(it.body() ?: emptyList())
                    Observable.just(it.body())
                } else {
                    Observable.just(testData())
                }
            }
    }


    private fun getPostsFromCache(): List<PostModel>? {
        val json = sharedPreferences.getString(Constants.LIST_POSTS_CACHE, "")
        val type = object : TypeToken<List<PostModel>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun addToCash(list: List<PostModel>) {
        val prefsEditor = sharedPreferences.edit()
        val json = Gson().toJson(list)
        //remove old data
        prefsEditor.remove(Constants.LIST_POSTS_CACHE).apply()
        //add new data
        prefsEditor.putString(Constants.LIST_POSTS_CACHE, json)
        prefsEditor.commit()
    }

    private fun testData(): ArrayList<PostModel> {
        return arrayListOf(
            PostModel(12, 2352, "Taiyr", "afjasjdfnsljnfssd"),
            PostModel(13, 1323, "Taiyr", "afjasjdfnsljnfssd"),
            PostModel(14, 2342, "Taiyr", "afjasjdfnsljnfssd"),
            PostModel(16, 8478, "Taiyr", "afjasjdfnsljnfssd")
        )
    }
}