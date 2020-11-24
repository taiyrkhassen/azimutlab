package com.example.azimutlab.dagger

import com.example.azimutlab.AzimutApp
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.dagger.modules.BaseScreen
import javax.inject.Inject

class SberScreen @Inject constructor(

): BaseScreen(){
    companion object {
        fun getScreen(someId: Int): BaseScreen = SberScreen()
    }
    fun getId() = 2
}