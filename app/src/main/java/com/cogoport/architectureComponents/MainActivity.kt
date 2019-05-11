package com.cogoport.architectureComponents

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.arch.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName
    var notesList: MutableList<Note>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "Lifecycle Owner onCreate()")
        lifecycle.addObserver(MainActivityLifecycleObserver())
        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val myRandomNumber = model.number as LiveData<String>
        myRandomNumber.observe(this, Observer { s ->
            // Update the UI
            //textView.setText(myRandomNumber.value.toString());
            Log.d(TAG, myRandomNumber.value.toString())
            Log.i(TAG, "Data Updated in UI")
        })

        //Room database with LiveData and ViewModel
        //so whenever we will add a new note to our room database it will added to it and then
        val noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer { notes ->
            notesList?.add(notes?.get(0)!!)
        })
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