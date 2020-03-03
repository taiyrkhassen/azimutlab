package com.example.azimutlab.dagger.modules

import com.example.azimutlab.mvp.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {
    @Provides
    fun provideMainRepository() : MainRepository{
        return MainRepository()
    }
}