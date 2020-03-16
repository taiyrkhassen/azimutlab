package com.example.azimutlab.mvvm.repository

import com.example.azimutlab.mvvm.models.PostModel
import io.reactivex.Observable

interface MainRepository {
    fun getPosts(): Observable<List<PostModel>>
}