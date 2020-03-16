package com.example.azimutlab.api

import com.example.azimutlab.mvvm.models.PostModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/posts/")
    fun getPostsJson(
    ): Observable<Response<List<PostModel>>>
}