package com.example.azimutlab.api_koin

import com.example.azimutlab.dagger.SberScreen

interface ApiAccount {

    //dynamic pishem my
    fun getAccountScreen(screenId: Int): SberScreen

    //koin daet zavisimost
    fun getCreditInteractor(): Any

}