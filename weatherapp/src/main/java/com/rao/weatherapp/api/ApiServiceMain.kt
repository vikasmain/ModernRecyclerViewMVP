package com.rao.weatherapp.api

import com.rao.weatherapp.model.Current.WeatherResponse
import com.rao.weatherapp.model.Forecast.ForecastResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServiceMain {
    @GET("current.json")
    fun maincategoryapi(@Query("key") key: String, @Query("q") city: String):
            Observable<WeatherResponse>

    @GET("forecast.json")
    fun forecastcategoryapi(@Query("key") key: String, @Query("q") city: String,
                            @Query("days") days: String): Observable<ForecastResponse>
}
