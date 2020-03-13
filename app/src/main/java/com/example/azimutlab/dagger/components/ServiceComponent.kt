package com.example.azimutlab.dagger.components

import com.example.azimutlab.api.ApiService
import com.example.azimutlab.dagger.modules.RepositoryModules
import com.example.azimutlab.dagger.modules.ServiceModule
import com.example.azimutlab.mvp.presenters.MainPresenter
import com.example.azimutlab.mvp.repository.MainRepositoryImpl
import dagger.Component

@Component(modules = [ServiceModule::class, RepositoryModules::class], dependencies = [AppComponent::class])
interface ServiceComponent {
    fun inject(presenter: MainPresenter)
    fun inject(repository: MainRepositoryImpl)
    fun getApiService():ApiService
}