package com.rao.weatherapp.model.Forecast

import com.rao.weatherapp.model.Current.Current
import com.rao.weatherapp.model.Current.Location

class ForecastResponse(var location: Location, var current: Current, var forecast: Forecast)
