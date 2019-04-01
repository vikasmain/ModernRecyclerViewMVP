package com.cogoport.architectureComponents

import android.arch.lifecycle.ViewModel
import android.util.Log

import java.util.Random

//Now whenever our Activity configuration changes then our random number will remain same because
//ViewModel is sending data to the UI on configuration changed.
class MainActivityViewModel : ViewModel() {

    private val TAG = this.javaClass.simpleName
    private var myRandomNumber: String? = null

    val number: String?
        get() {
            Log.i(TAG, "Get number")
            if (myRandomNumber == null) {
                createNumber()
            }
            return myRandomNumber
        }

    private fun createNumber() {
        Log.i(TAG, "Create new number")
        val random = Random()
        myRandomNumber = "Number: " + (random.nextInt(10 - 1) + 1)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }
}