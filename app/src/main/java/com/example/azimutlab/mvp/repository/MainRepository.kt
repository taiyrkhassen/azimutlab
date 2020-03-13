package com.example.azimutlab.mvp.repository

import com.example.azimutlab.mvp.models.PostModel
import io.reactivex.Observable
interface MainRepository {
    fun getPosts(): Observable<List<PostModel>>
}