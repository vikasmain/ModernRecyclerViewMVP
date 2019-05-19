package com.rao.weatherapp.components

import com.rao.weatherapp.api.NetworkModule
import com.rao.weatherapp.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface MainActivityComponent {
    fun build(): MainActivityComponent
}



