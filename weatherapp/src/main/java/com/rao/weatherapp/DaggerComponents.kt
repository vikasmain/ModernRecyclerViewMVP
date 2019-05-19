package com.rao.weatherapp

import com.rao.weatherapp.components.MainActivityComponent

class DaggerComponents {
    val daggerComponent: MainActivityComponent by lazy {
        getCompatDaggerComponent()
    }
    fun getCompatDaggerComponent(): MainActivityComponent {
        return MainActivityDaggerComponent.builder()
                .build()
    }
}