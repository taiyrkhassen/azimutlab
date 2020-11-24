package com.example.azimutlab.api

import com.example.azimutlab.mvvm.models.PostModel
import io.reactivex.Observable
import retrofit2.Response

interface ApiHelper {
    fun getPostJson(screenId: Int): Observable<Response<List<PostModel>>>
}