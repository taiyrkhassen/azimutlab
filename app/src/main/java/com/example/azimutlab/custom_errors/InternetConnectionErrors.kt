package com.example.azimutlab.custom_errors

import com.example.azimutlab.AzimutApp
import com.example.azimutlab.R
import java.io.IOException

class NoConnectivityException : IOException() {
    val context = AzimutApp.getApplicationComponent().getContext()
    override val message: String
        get() =
            context.getString(R.string.no_network_available)
}

class NoInternetException() : IOException() {
    val context = AzimutApp.getApplicationComponent().getContext()
    override val message: String
        get() =
            context.getString(R.string.check_connected_wifi)
}