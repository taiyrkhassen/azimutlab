package com.example.azimutlab.dagger.components

import android.content.Context
import android.content.SharedPreferences
import com.example.azimutlab.dagger.modules.AppModule
import com.example.azimutlab.dagger.modules.NetworkModule
import com.example.azimutlab.dagger.modules.RepositoryModules
import com.example.azimutlab.dagger.modules.ViewModelModule
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModules::class, ViewModelModule::class])
interface AppComponent {
    fun getContext(): Context
    fun getRetrofit(): Retrofit
    fun getPrefs(): SharedPreferences
}