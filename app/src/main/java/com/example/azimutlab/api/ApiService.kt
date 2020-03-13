package com.example.azimutlab.api

import com.example.azimutlab.mvp.models.PostModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts/")
    fun getPostsJson(
    ): Observable<Response<List<PostModel>>>
}