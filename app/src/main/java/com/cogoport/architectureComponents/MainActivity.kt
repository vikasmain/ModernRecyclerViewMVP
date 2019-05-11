package com.cogoport.architectureComponents

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.arch.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "Lifecycle Owner onCreate()")
        lifecycle.addObserver(MainActivityLifecycleObserver())
        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val myRandomNumber = model.number
        Log.d(TAG, myRandomNumber)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Owners onStart()")
        //it will call first before observer onStart() event because that is event
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Owners onResume()")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Owners onPause()")

    }
}