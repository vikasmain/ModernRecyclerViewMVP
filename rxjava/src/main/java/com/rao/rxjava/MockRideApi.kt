package com.rao.rxjava

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MockRideApi {

    fun getEstimates(): Observable<Country> {
        return Observable.just(
                Country(
                        country = "india",
                        city = listOf(
                                CityDesc(
                                        zipcode = "110094",
                                        peopleRange = "10000",
                                        currency = "Rupee",
                                        image = R.drawable.car_ic_drive_placeholder,
                                        isDevelopingCountry = false
                                ),
                                CityDesc(
                                        zipcode = "110083",
                                        peopleRange = "1-6 million",
                                        currency = "Dollar",
                                        image = R.drawable.car_ic_drive_placeholder,
                                        isDevelopingCountry = true
                                ),
                                CityDesc(
                                        zipcode = "110093",
                                        peopleRange = "1-4",
                                        currency = "Dinar",
                                        image = R.drawable.car_ic_drive_placeholder,
                                        isDevelopingCountry = false
                                ),
                                CityDesc(
                                        zipcode = "11028",
                                        peopleRange = "1-4 billion",
                                        currency = "Rupee",
                                        image = R.drawable.car_ic_drive_placeholder,
                                        isDevelopingCountry = false
                                )
                        )
                )
        ).delay(5, TimeUnit.SECONDS)
    }
}


data class Country(
        val city: List<CityDesc>,
        val country: String
)


data class CityDesc(
        var zipcode: String,
        val peopleRange: String,
        val currency: String,
        var image: Int,
        val isDevelopingCountry: Boolean
)
