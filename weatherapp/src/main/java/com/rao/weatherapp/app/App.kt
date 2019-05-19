package com.rao.weatherapp.app

import android.content.Context
import com.rao.weatherapp.DaggerDepsHolder
import com.rao.weatherapp.DepsProvider
import com.rao.weatherapp.AppDaggerDepsHolderProvider

class App : DaggerDepsHolder(), AppDaggerDepsHolderProvider {
    override var DepsHolder: DepsProvider=this


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        create(this)
    }

    companion object {
        lateinit var context: Context
    }
}
