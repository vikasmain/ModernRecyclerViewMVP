package com.rao.weatherapp.api

import com.rao.weatherapp.app.App
import com.rao.weatherapp.view.MainActivityView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.rao.weatherapp.presenter.MainActivityPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


@Module
abstract class NetworkModule(view: MainActivityView) {
    var mainActivityView: MainActivityView

    init {
        mainActivityView = view

    }

    private val REQUEST_TIMEOUT = 30
    private var okHttpClient: OkHttpClient? = null

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val BASE_URL = "https://api.apixu.com/v1/"

        if (okHttpClient == null)
            initOkHttp()
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


    //for logging information of various network requests
    fun initOkHttp(): OkHttpClient? {
        val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")


            val request = requestBuilder.build()
            chain.proceed(request)
        }
        okHttpClient = httpClient.build()

        return okHttpClient
    }

    //provides ApiServices
    @Provides
    @Singleton
    fun providesNetworkService(
            retrofit: Retrofit): ApiServiceMain {
        return retrofit.create<ApiServiceMain>(ApiServiceMain::class.java)
    }

    //provides View
    @Provides
    fun providesview(): MainActivityView {
        return mainActivityView
    }


    @Binds
    abstract fun bindsMainActivityPresenter(mainActivityPresenter:
                                                   MainActivityPresenter):
            MainActivityPresenter

    @Provides
    @Singleton
    fun provideFusedApi(): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(App.context)
    }
}
