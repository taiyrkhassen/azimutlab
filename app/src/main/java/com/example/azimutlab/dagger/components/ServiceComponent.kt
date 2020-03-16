package com.example.azimutlab.dagger.components

import com.example.azimutlab.api.ApiService
import com.example.azimutlab.dagger.modules.RepositoryModules
import com.example.azimutlab.dagger.modules.ServiceModule
import com.example.azimutlab.dagger.modules.ViewModelModule
import com.example.azimutlab.mvvm.repository.MainRepositoryImpl
import com.example.azimutlab.mvvm.view.activities.MainActivity
import com.example.azimutlab.mvvm.viewmodels.MainViewModel
import dagger.Component

@Component(modules = [ServiceModule::class, RepositoryModules::class, ViewModelModule::class], dependencies = [AppComponent::class])
interface ServiceComponent {
    fun inject(repository: MainRepositoryImpl)
    fun inject(activity: MainActivity)
    fun inject(mainViewModel: MainViewModel)
    fun getApiService():ApiService
}