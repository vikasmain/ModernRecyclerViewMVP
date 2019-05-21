package com.rao.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.reactivex.Observer

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import java.util.ArrayList


class Check : AppCompatActivity() {
    lateinit internal var recyclerView: RecyclerView
    lateinit internal var textView: TextView
    lateinit internal var testapi: MockRideApi
    internal var adapter = CarDetailsAdapter(this)
    internal var arrayList: List<CityDesc> = ArrayList()
    lateinit var disposable: Disposable
    lateinit internal var ans: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testapi = MockRideApi()
        recyclerView = findViewById<View>(R.id.list_view_repos) as RecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
//        textView = findViewById<View>(R.id.test) as TextView
        getcall()
    }
//
//    Disposable is used to dispose the subscription when an Observer no longer wants to listen to Observable. In android disposable are very
// useful in avoiding memory leaks.
//
//    Let’s say you are making a long running network call and updating the UI. By the time network call completes its work,
// if the activity / fragment is already destroyed, as the Observer subscription is still alive, it tries to update already destroyed
// activity. In this case it can throw a memory leak. So using the Disposables, the un-subscription can be when the activity is destroyed.


    fun getcall() {
        testapi.getEstimates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Country> {
                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(estimateV1Response: Country) {
                        ans = estimateV1Response.country
                        arrayList = estimateV1Response.city
                        adapter.setGitHubRepos(arrayList)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}
