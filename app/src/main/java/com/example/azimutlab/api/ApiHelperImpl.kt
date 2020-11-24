package com.example.azimutlab.api

import com.example.azimutlab.mvvm.models.PostModel
import io.reactivex.Observable
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override fun getPostJson(screenId: Int): Observable<Response<List<PostModel>>> =
        apiService.getPostsJson(screenId = screenId)

}