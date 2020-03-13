package com.example.azimutlab.dagger.modules

import android.content.SharedPreferences
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.mvp.presenters.MainPresenter
import com.example.azimutlab.mvp.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import moxy.presenter.ProvidePresenter

@Module
class RepositoryModules {
    @Provides
    fun provideMainRepository(apiService: ApiService, sharedPreferences: SharedPreferences): MainRepositoryImpl {
        return MainRepositoryImpl(apiService, sharedPreferences)
    }

}