package com.example.azimutlab.api_koin

import android.content.Context
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.dagger.SberScreen

//providnem cherez koin
//nujno test napisat

class AccountFeatureHolderTest(private val context: Context) : BaseFeatureHolder {

    override fun buildFeature(): ApiAccount {
        return object : ApiAccount {

            //dynamic screen id
            override fun getAccountScreen(screenId: Int): SberScreen {
                return SberScreen().newInstance(screenId = 123)
            }

            //providing with koin
            override fun getCreditInteractor(): Any {
                return AzimutApp.koinTest.get<ApiService>()
            }

        }
    }


}