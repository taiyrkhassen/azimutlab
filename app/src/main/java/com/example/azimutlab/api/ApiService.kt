package com.example.azimutlab.api

import com.example.azimutlab.mvp.models.PostModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("/{post_id}/posts/")
    fun getPostsJson(
        @Path("post_id") post_id: Int
    ):Observable<Response<List<PostModel>>>
}