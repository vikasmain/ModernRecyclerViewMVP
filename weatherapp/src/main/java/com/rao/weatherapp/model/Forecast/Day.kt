package com.rao.weatherapp.model.Forecast

class Day(var maxtemp_c: Double, var maxtemp_f: Double, var mintemp_c: Double, var mintemp_f: Double, var avgtemp_c: Double,
          var avgtemp_f: Double, var maxwind_mph: Double, var maxwind_kph: Double, totalprecip_mm: Int, totalprecip_in: Int,
          var avgvis_km: Double, avgvis_miles: Int, avghumidity: Int, var condition: Condition2, var uv: Double)