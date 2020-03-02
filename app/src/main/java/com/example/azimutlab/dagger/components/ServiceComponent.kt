package com.example.azimutlab.dagger.components

import com.example.azimutlab.dagger.modules.AppModule
import com.example.azimutlab.dagger.modules.ServiceModule
import com.example.azimutlab.mvp.presenters.MainPresenter
import dagger.Component

@Component(modules = [ServiceModule::class, AppModule::class])
interface ServiceComponent {
    fun inject(presenter: MainPresenter)

}