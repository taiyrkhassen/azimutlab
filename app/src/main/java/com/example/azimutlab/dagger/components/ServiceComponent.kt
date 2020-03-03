package com.example.azimutlab.dagger.components

import com.example.azimutlab.api.ApiService
import com.example.azimutlab.dagger.modules.AppModule
import com.example.azimutlab.dagger.modules.ServiceModule
import com.example.azimutlab.mvp.presenters.MainPresenter
import com.example.azimutlab.mvp.repository.MainRepository
import dagger.Component

@Component(modules = [ServiceModule::class], dependencies = [AppComponent::class])
interface ServiceComponent {
    fun inject(presenter: MainPresenter)
    fun inject(repository: MainRepository)
    fun getApiService():ApiService
}