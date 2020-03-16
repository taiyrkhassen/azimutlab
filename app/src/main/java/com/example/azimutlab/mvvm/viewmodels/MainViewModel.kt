package com.example.azimutlab.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.mvvm.repository.MainRepositoryImpl
import javax.inject.Inject

//reference to repository
class MainViewModel: ViewModel() {
    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.appComponent)
            .build()
            .inject(this)
    }

    @Inject
    lateinit var mainRepos: MainRepositoryImpl



}