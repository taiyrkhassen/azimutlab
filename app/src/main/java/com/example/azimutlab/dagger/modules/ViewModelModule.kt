package com.example.azimutlab.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.azimutlab.dagger.ViewModelFactory
import com.example.azimutlab.dagger.ViewModelKey
import com.example.azimutlab.mvvm.repository.MainRepositoryImpl
import com.example.azimutlab.mvvm.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

//Это то чем потом мы будем анонсировать модели в модуле:
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}