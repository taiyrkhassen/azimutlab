package com.example.azimutlab.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.azimutlab.custom_errors.NoConnectivityException
import com.example.azimutlab.custom_errors.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class NoInternetConnectInterceptor(private val context: Context) :
    Interceptor {

    private fun isConnectedToInternet() : Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork != null
        } else {
            val netInfo = connectivityManager.activeNetworkInfo
            netInfo != null && netInfo.isConnectedOrConnecting
        }
    }

    //stackOverFlow code to check available internet
    private fun isInternetAvailable() : Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockAdr = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockAdr, timeoutMs)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun intercept(chain: Interceptor.Chain) : Response {
        return if (!isConnectedToInternet()) {
            throw NoConnectivityException()
        } else if (!isInternetAvailable()) {
            throw NoInternetException()
        } else {
            chain.proceed(chain.request())
        }
    }

}


