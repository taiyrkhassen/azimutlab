package com.example.azimutlab.koin

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import android.util.Log
import com.example.azimutlab.Constants
import com.example.azimutlab.Constants.BASE_URL
import com.example.azimutlab.api.ApiHelper
import com.example.azimutlab.api.ApiHelperImpl
import com.example.azimutlab.api.ApiService
import com.example.azimutlab.api_koin.AccountFeatureHolderTest
import com.example.azimutlab.api_koin.BaseFeatureHolder
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//здесь мы провайдим все зависимости


val appModule = module {
    //делаем акаунт фючер модуль
    single{ provideOkHttpClient(interceptor = get(), httpLoggingInterceptor = get()) }
    single { provideRetrofit(okHttpClient = get(), provideRxAdapter = get()) }

    single { provideApiService(get()) }

    single { provideSharedPref(androidContext()) }
    single { provideRxAdapter() }
    single { provideHttpLoggingInterceptor() }
    single { provideInterceptor(get(), androidContext()) }

    single<BaseFeatureHolder> {
        return@single provideAccountFeatureHolder(androidContext())
    } //как синглтон есть еще factory — Предоставление зависимости как фабричный компонент, т.е. создает каждый раз новый экземпляр

    single<ApiHelper> {
        return@single ApiHelperImpl(apiService = get())
    }
}

private fun provideAccountFeatureHolder(context: Context): AccountFeatureHolderTest {
    return AccountFeatureHolderTest(context)
}

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)  //любая апи


private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    provideRxAdapter: RxJava2CallAdapterFactory
):
        Retrofit {
    return Retrofit.Builder()
        .addCallAdapterFactory(provideRxAdapter)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}


fun provideInterceptor(sharedPreferences: SharedPreferences, context: Context):
        Interceptor {
    return Interceptor { chain ->
        val request = chain.request().newBuilder()
        val userToken = sharedPreferences.getString(Constants.TOKEN, "")
        var versionApp = ""

        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionApp = pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        if (userToken != "") {
            request.addHeader("auth-token", userToken!!)
            Log.v("*************** Mine: ", "token is = $userToken")
        }

        request.addHeader("source", "android")
        request.addHeader("version", versionApp)

        chain.proceed(request.build())
    }
}


fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.HEADERS
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

fun provideOkHttpClient(
    interceptor: Interceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(StethoInterceptor())
        .addNetworkInterceptor(interceptor)
        .build()
}


fun provideRxAdapter(): RxJava2CallAdapterFactory {
    return RxJava2CallAdapterFactory.create()
}


fun provideSharedPref(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}
