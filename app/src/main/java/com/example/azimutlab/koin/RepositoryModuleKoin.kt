package com.example.azimutlab.koin

import com.example.azimutlab.mvvm.repository.MainRepositoryImpl
import org.koin.dsl.module


val repoModule = module {
    single {
        MainRepositoryImpl(get(), get())
    }
}
