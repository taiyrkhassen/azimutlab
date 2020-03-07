package com.example.azimutlab.dagger.modules

import android.content.Context
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.mvp.presenters.MainPresenter
import com.example.azimutlab.mvp.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {
    @Provides
    fun provideMainRepository(apiService: ApiService) : MainRepository{
        return MainRepository(apiService)
    }

    @Provides
    fun providePresenter(mainRepository: MainRepository):MainPresenter{
        return MainPresenter(mainRepository)
    }
}