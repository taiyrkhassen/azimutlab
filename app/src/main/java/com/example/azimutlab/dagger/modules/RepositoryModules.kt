package com.example.azimutlab.dagger.modules

import com.example.azimutlab.api.ApiService
import com.example.azimutlab.mvp.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {
    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepositoryImpl {
        return MainRepositoryImpl(apiService)
    }

//    @Provides
//    fun providePresenter(mainRepository: MainRepository):MainPresenter{
//        return MainPresenter(mainRepository)
//    }
}