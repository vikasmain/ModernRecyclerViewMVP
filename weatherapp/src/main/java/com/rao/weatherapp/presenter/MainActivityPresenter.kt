package com.rao.weatherapp.presenter

interface MainActivityPresenter {

    fun loadForecast(cityName: String)

    fun loadCurrent(cityName: String)
}
