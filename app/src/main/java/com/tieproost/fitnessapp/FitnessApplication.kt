package com.tieproost.fitnessapp

import android.app.Application
import com.tieproost.fitnessapp.data.AppContainer
import com.tieproost.fitnessapp.data.DefaultAppContainer

class FitnessApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}
