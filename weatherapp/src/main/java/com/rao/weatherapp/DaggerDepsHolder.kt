package com.rao.weatherapp

import android.app.Application
import android.content.Context

open class DaggerDepsHolder:DepsProvider, Application(){
    lateinit var daggerComponents:DaggerComponents
    override fun provideDeps(): DevDeps {
        return daggerComponents.daggerComponent
    }
    fun create(context: Context) {
        daggerComponents = DaggerComponents()
    }
}

interface AppDaggerDepsHolderProvider {
    var DepsHolder: DepsProvider
}
