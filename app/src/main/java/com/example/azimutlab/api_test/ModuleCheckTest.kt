package com.example.azimutlab.api_test

import android.content.Context
import com.example.azimutlab.api_koin.BaseFeatureHolder
import com.example.azimutlab.koin.appModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.android.ext.koin.androidContext
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.inject

@Category(CheckModuleTest::class)
class ModuleCheckTest : AutoCloseKoinTest(){


    @Test
    fun checkModules() = org.koin.test.check.checkModules {
        modules(appModule)
    }

}