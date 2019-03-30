package com.rao.weatherapp.model.Current

class Current(var last_updated_epoch: Int, var last_updated: String, var temp_c: Double, var temp_f: Double, var is_day: Int,
              var condition: Condition, var wind_mph: Double, var wind_kph: Double, var wind_degree: Double, var wind_dir: String,
              var pressure_mb: Double, var pressure_in: Double, var precip_mm: Double, var precip_in: Double, var humidity: Double,
              var cloud: Double, var feelslike_c: Double, var feelslike_f: Double, var vis_km: Double, var vis_miles: Double, var uv: Int)
