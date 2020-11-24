package com.example.azimutlab

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDexApplication
import com.example.azimutlab.Constants.BASE_URL
import com.example.azimutlab.dagger.components.AppComponent
import com.example.azimutlab.dagger.components.DaggerAppComponent
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.dagger.components.ServiceComponent
import com.example.azimutlab.dagger.modules.AppModule
import com.example.azimutlab.dagger.modules.ServiceModule
import com.example.azimutlab.koin.appModule
import com.example.azimutlab.koin.repoModule
import com.example.azimutlab.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AzimutApp : MultiDexApplication() {
    companion object {
        fun get(activity: Activity): AzimutApp {
            return activity.application as AzimutApp
        }

        lateinit var appComponent: AppComponent
        lateinit var serviceComponent: ServiceComponent

        fun getApplicationComponent(): AppComponent {
            return appComponent
        }

    }

    override fun onCreate() {
        super.onCreate()
        createAppComponent()
        createServiceComponent()
        startKoin {
            androidContext(this@AzimutApp)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this, BASE_URL))
            .build()
    }

    private fun createServiceComponent() {
        serviceComponent = DaggerServiceComponent
            .builder()
            .appComponent(appComponent)
            .build()
    }

}