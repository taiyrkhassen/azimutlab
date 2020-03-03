package com.example.azimutlab.dagger.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {
    var context: Context
    var baseUrl: String

    constructor(context: Context, baseUrl: String) {
        this.context = context
        this.baseUrl = baseUrl
    }


    @Provides
    fun context(): Context {
        return context
    }

    // here create retrofit
    @Provides
    fun retrofitBuild(okHttpClient: OkHttpClient, provideRxAdapter: RxJava2CallAdapterFactory): Retrofit{
        return Retrofit.Builder()
            .addCallAdapterFactory(provideRxAdapter)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getSharedPref(context: Context): SharedPreferences{
        return context.getSharedPreferences("taiyr", 0)
    }

    @Provides
    fun provideRxAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
    @Provides
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder().build()
    }

}