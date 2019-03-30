package com.rao.weatherapp.presenter

import android.util.Log
import com.rao.weatherapp.BuildConfig
import com.rao.weatherapp.api.ApiServiceMain
import com.rao.weatherapp.model.Current.WeatherResponse
import com.rao.weatherapp.model.Forecast.ForecastResponse
import com.rao.weatherapp.view.MainActivity.Companion.TAG
import com.rao.weatherapp.view.MainActivityView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat


const val NO_OF_DAYS="5"
class MainActivityPresenterImpl(val mainActivityView: MainActivityView,
                                val serviceMain: ApiServiceMain) : MainActivityPresenter {

    override fun loadForecast(cityName: String) {
        //always load api keys like stuff(private stuffs) from gradle
        mainActivityView.showProgress()
        val forecastobserver =
                serviceMain.forecastcategoryapi(BuildConfig.Apikey, cityName, NO_OF_DAYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        forecastobserver.subscribe(object : Observer<ForecastResponse> {

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(response: ForecastResponse) {
                lateinit var forecastList: MutableList<Pair<String, String>>
                forecastList = mutableListOf()
                mainActivityView.hideProgress()
                Log.d(TAG, "In onNext()")
                //Don't use Locale.getDefault() it is machine dependent use simpledateformat for
                //casting date
                val format = SimpleDateFormat("yyyy-MM-dd")
                val day = SimpleDateFormat("EEEE")
                val formatedDate1 = format.parse(response.forecast.forecastday[1].date)
                val finalDay1 = day.format(formatedDate1)
                val formatedDate2 = format.parse(response.forecast.forecastday[2].date)
                val finalDay2 = day.format(formatedDate2)
                val formatedDate3 = format.parse(response.forecast.forecastday[3].date)
                val finalDay3 = day.format(formatedDate3)
                val formatedDate4 = format.parse(response.forecast.forecastday[4].date)
                val finalDay4 = day.format(formatedDate4)

                forecastList.add(Pair(finalDay1,
                        "${response.forecast.forecastday[1].day.avgtemp_c} C"))
                forecastList.add(Pair(finalDay2,
                        "${response.forecast.forecastday[2].day.avgtemp_c} C"))
                forecastList.add(Pair(finalDay3,
                        "${response.forecast.forecastday[3].day.avgtemp_c} C"))
                forecastList.add(Pair(finalDay4,
                        "${response.forecast.forecastday[4].day.avgtemp_c} C"))

                mainActivityView.displayForecast(forecastList)

            }

            override fun onError(e: Throwable) {
                mainActivityView.hideProgress()
                mainActivityView.errorHandler()
                Log.d(TAG, e.message)
            }

            override fun onComplete() {
            }
        })
    }

    override fun loadCurrent(cityName: String) {
        mainActivityView.showProgress()
        val current =
                 serviceMain.maincategoryapi(BuildConfig.Apikey, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        current.subscribe(object : Observer<WeatherResponse> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onNext(response: WeatherResponse) {
                mainActivityView.hideProgress()
                mainActivityView.displayCurrent(response)
                Log.d(TAG, "In onNext()")
            }
            override fun onError(e: Throwable) {
                mainActivityView.hideProgress()
                mainActivityView.errorHandler()
                Log.d(TAG, e.message)

            }
            override fun onComplete() {
            }
        })
    }
}
