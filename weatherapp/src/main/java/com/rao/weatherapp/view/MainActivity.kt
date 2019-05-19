package com.rao.weatherapp.view

import android.Manifest
import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import com.rao.weatherapp.api.NetworkModule
import com.rao.weatherapp.presenter.MainActivityPresenterImpl
import javax.inject.Inject
import android.os.Build
import android.support.v7.app.AlertDialog
import android.location.Geocoder
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.widget.Toast
import com.rao.weatherapp.app.App
import com.google.android.gms.location.*
import com.rao.weatherapp.DepsProvider
import com.rao.weatherapp.adapter.ForecastAdpater
import com.rao.weatherapp.components.DaggerMainActivityComponent
import com.rao.weatherapp.model.Current.WeatherResponse
import com.rao.weatherapp.presenter.MainActivityPresenter
import java.util.*


class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    lateinit var slideUp: Animation
    lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rao.weatherapp.R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this@MainActivity) as RecyclerView.LayoutManager?
//        val myComponent = DaggerMainActivityComponent
//                .builder()
//                .networkModule(NetworkModule(this@MainActivity))
//                .build()
//        myComponent.inject(this@MainActivity)
//        above one was an old way of injecting dependencies using dagger

        //new approach of injecting dependencies in Activity(UI)
        (applicationContext as DepsProvider).provideDeps().inject(this)
        if (!checkPermission()) {
            requestPermission(PERMISSION_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()
        slideUp = AnimationUtils.loadAnimation(applicationContext,
                com.rao.weatherapp.R.anim.popup_show)
        if (checkPermission()) {
            fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location: Location ->
                        val longitude = location.longitude;
                        val latitude = location.latitude;
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                        if (addresses.size > 0) {

                            cityName = addresses.get(0).locality
                            mainActivityPresenter.loadCurrent(cityName)
                            mainActivityPresenter.loadForecast(cityName)
                        }
                    }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0) {

                val locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val coarseAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED

                if (locationAccepted && coarseAccepted) {
                    //always use tags with logs and Toasts messages
                    Log.d(TAG, "Permission Granted")
                } else {

                    Toast.makeText(this, TAG+"Permission Denied", Toast.LENGTH_LONG).show()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel("Please allow access to both the permissions",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface, which: Int) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(arrayOf<String>(
                                                        ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                                                        PERMISSION_REQUEST_CODE)
                                            }
                                        }
                                    })
                            return
                        }
                    }

                }
            }
        }
    }

    fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(App.context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show()
    }

    fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(App.context, Manifest.permission.ACCESS_FINE_LOCATION)
        val result1 = ContextCompat.checkSelfPermission(App.context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(int: Int) {
        ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), int)

    }

    override fun displayForecast(forecastResponse: MutableList<kotlin.Pair<String, String>>) {
        val forecastAdapter = ForecastAdpater(forecastResponse, this)
        recycler_view.adapter = forecastAdapter
        recycler_view.startAnimation(slideUp)
    }

    override fun showProgress() {
        progressCircular.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressCircular.visibility = View.GONE
    }

    override fun displayCurrent(mainCategory: WeatherResponse) {
        Log.d(TAG, mainCategory.toString())
        temp.text = mainCategory.current.temp_c.toString() + getString(com.rao.weatherapp.R.string.degree)
        item_name.text = mainCategory.location.name
    }

    override fun errorHandler() {
        mainLayout.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        button.setOnClickListener {
            mainActivityPresenter.loadCurrent(cityName)
            mainActivityPresenter.loadForecast(cityName)
            mainLayout.visibility = View.VISIBLE
            errorLayout.visibility = View.GONE
        }
    }

    companion object {
        val PERMISSION_REQUEST_CODE = 1
        val TAG=MainActivity::class.qualifiedName
    }
}

