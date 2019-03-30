package com.rao.weatherapp.view

import com.rao.weatherapp.model.Current.WeatherResponse

interface MainActivityView {
    fun displayCurrent(mainCategory: WeatherResponse)
    fun displayForecast(forecastResponse: MutableList<Pair<String, String>>)
    fun showProgress()
    fun hideProgress()
    fun errorHandler()
}
