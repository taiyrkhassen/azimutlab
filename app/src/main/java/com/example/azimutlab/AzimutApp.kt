package com.example.azimutlab

import android.app.Activity
import android.app.Application

class AzimutApp : Application(){
    companion object{

        fun get(activity: Activity): AzimutApp {
            return activity.application as AzimutApp
        }



    }

}