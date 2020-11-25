package com.example.azimutlab.api_test

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.azimutlab.api_koin.BaseFeatureHolder
import com.example.azimutlab.koin.appModule
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

class KoinAppTest : KoinTest, Application(){

    private val componentHolder: BaseFeatureHolder by inject()

    @Before
    fun setUp(){

    }

    @Test
    fun makeTest() {
        startKoin {
            androidContext(this@KoinAppTest)
            modules(appModule)
        }
        val featureHolder = componentHolder.buildFeature()
        val screen = featureHolder.getAccountScreen(1)


        assertEquals("SberScreen returned", screen.getNameScreen())

    }

    @After
    fun after() {
        stopKoin()
    }

}