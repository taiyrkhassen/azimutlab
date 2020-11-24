package com.example.azimutlab.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.azimutlab.mvvm.models.PostModel
import com.example.azimutlab.mvvm.repository.MainRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class ViewModelTestKoin(
    private val mainRepos: MainRepositoryImpl
) : BaseViewModel() {

    val listPostLiveData by lazy { MutableLiveData<List<PostModel>>() }
    val isLoading by lazy { MutableLiveData<Boolean>() }
    val error by lazy { MutableLiveData<Exception>() }

    fun getData() {
        isLoading.value = true
        addDisposable(
            mainRepos.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading.value = false
                    listPostLiveData.value = it
                }, {
                    isLoading.value = false
                    error.value = it as Exception?
                })
        )
    }
}