package com.cogoport.architectureComponents

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

class MainActivityLifecycleObserver:LifecycleObserver{
    val TAG = this.javaClass.simpleName
    //the observer class uses the annotation onLifecycle event to keep track the events
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent(){
        Log.d(TAG,"in Observer onCreate()")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartEvent(){
        Log.d(TAG,"in Observer onStart()")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent(){
        Log.d(TAG,"in Observer onResume()")
    }
}