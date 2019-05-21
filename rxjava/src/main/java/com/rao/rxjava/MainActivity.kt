package com.rao.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import rx.Subscription


class MainActivity : AppCompatActivity() {
    private val adapter = GitHubRepoAdapter()
    private val subscription: Subscription? = null
    lateinit var disposableTwo: Disposable
    lateinit var ans: String
    lateinit var disposable: Disposable
    lateinit var textview: TextView
    lateinit var subscriber: Subscription
    internal var testDataApi = TestDataApi()
    lateinit var observable2: Observable<Desc>
    lateinit var observable: Observable<String>
    lateinit var disposableThree: Disposable
    val compdisposable = CompositeDisposable()
    var output = mutableListOf<String>()
    var list = mutableListOf<String>()
    lateinit var disposableObserverOne: DisposableObserver<String>
    lateinit var disposableObserverTwo: DisposableObserver<String>
    internal var recyclerAdapter = GitHubRecycler(this)
    lateinit internal var recyclerView: RecyclerView
    lateinit var disposableFour: Disposable
    var checkList = mutableListOf<Desc>()
    var ansList = mutableListOf<String>()
    internal var testApi = TestApi()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observable2 = testDataApi.getData()
        observable = testApi.compositeObserver
        disposableObserverOne = getObserverOne()
        disposableObserverTwo = getObserverTwo()
        recyclerView = findViewById<RecyclerView>(R.id.list_view_repos)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        getCall()
        gettest()
        tesview.text = list.toString()
        getAgain()
        pesview.text = checkList.toString()
        getGhost()
        mesview.text = ansList.toString()
    }

    fun getGhost() {
        disposableFour = testApi.ghost.subscribe()
        { it ->
            ansList.add(it)
        }
    }

    fun getAgain() {
        disposableThree = testDataApi.getData().subscribe() { x -> checkList.add(x) }
    }

    override fun onResume() {
        super.onResume()
        compdisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it -> it.startsWith("S") }
                .subscribeWith(disposableObserverOne))
        compdisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it -> it.startsWith("F") }
                .subscribeWith(disposableObserverTwo))
        Log.d("oup", output.toString())
    }

    fun getCall() {
        disposableTwo = testApi.range
                .subscribe { x -> list.add(x.toString()) }
    }


    override fun onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
        super.onDestroy()
    }

    private fun gettest() {
        disposable = testApi.filter.subscribe { x -> list.add(x.toString()) }
    }

    private fun getObserverOne(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {

            override fun onNext(s: String) {
                Log.d(TAG, "Output from One: $s")
                output.add(s)

            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "in Oncomplete()")
            }
        }
    }

    private fun getObserverTwo(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {


            override fun onNext(s: String) {
                Log.d(TAG, "Output from two: $s")
                output.add(s)

            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "in Oncomplete()")
                resview.text = "Composite Disposable Output is " + output.toString()

            }
        }

    }

// adding an Observable to the disposable

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    //    private void getStarredRepos(String username) {
    //        subscription = GitHubClient.getInstance()
    //                .getStarredRepos(username)
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(new Observer<List<GitHubRepo>>() {
    //                    @Override
    //                    public void onCompleted() {
    //                        Log.d(TAG, "In onCompleted()");
    //                    }
    //
    //                    @Override
    //                    public void onError(Throwable e) {
    //                        e.printStackTrace();
    //                        Log.d(TAG, "In onError()");
    //                    }
    //
    //                    @Override
    //                    public void onNext(List<GitHubRepo> gitHubRepos) {
    //                        Log.d(TAG, "In onNext()");
    //                        Log.d(TAG,gitHubRepos.toString());
    //                        recyclerAdapter.setGitHubRepos(gitHubRepos);
    //                    }
    //                });
    //    }
    //
}
