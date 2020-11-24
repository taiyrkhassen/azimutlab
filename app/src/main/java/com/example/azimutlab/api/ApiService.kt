package com.example.azimutlab.api

import com.example.azimutlab.mvvm.models.PostModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("/posts/")
    fun getPostsJson(
        @Header("screeen") screenId: Int
    ): Observable<Response<List<PostModel>>>
}