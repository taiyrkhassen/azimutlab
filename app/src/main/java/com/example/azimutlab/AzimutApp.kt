package com.example.azimutlab

import android.app.Activity
import android.app.Application
import com.example.azimutlab.Constants.BASE_URL
import com.example.azimutlab.dagger.components.AppComponent
import com.example.azimutlab.dagger.components.DaggerAppComponent
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.dagger.components.ServiceComponent
import com.example.azimutlab.dagger.modules.AppModule
import com.example.azimutlab.dagger.modules.ServiceModule

class AzimutApp : Application() {
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