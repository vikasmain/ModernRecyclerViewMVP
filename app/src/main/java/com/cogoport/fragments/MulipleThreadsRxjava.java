package com.cogoport.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cogoport.MvpContract.RxjavaRetrofitcontract;
import com.cogoport.R;
import com.cogoport.adapter.CouponsAdapter;
import com.cogoport.api.StoreCouponApi;
import com.cogoport.model.StoreCoupons;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */

 //retrofit with RxJava in android for
 //making background calls with parallel processing and updating view objects from background process.
public class MulipleThreadsRxjava extends Fragment implements RxjavaRetrofitcontract.MultipleMvpViewRxjava,
        RxjavaRetrofitcontract.MvpPresenterApi{

    @BindView(R.id.coupon_rv)
    RecyclerView couponRecyclerView;
    ProgressDialog progressDialog;
    CompositeDisposable compositeDisposable;
    Retrofit retrofit;
    View v;
    public MulipleThreadsRxjava() {
    }
    public static final String BASE_URL = "http://www.zoftino.com/api/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_muliple_threads_rxjava, container, false);
        progressDialog=new ProgressDialog(getContext());
        ButterKnife.bind(this,v);
        initRecyclerView();
        retrofitobject();
        showCoupons(v);
        showCouponsTopStore(v);


     return v;
    }
    @Override
    public void initRecyclerView() {

        couponRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        couponRecyclerView.setLayoutManager(layoutManager);
    }



    public void showCoupons(View view){
        getcouponData();
    }
    public void showCouponsTopStore(View view){
        getStoreCouponData();
    }
    //two Retrofit service calls execute parallel using RxJava
    public void getStoreCouponData(){
        //first it creates an observable which emits retrofit service class
        //to leave current main thread, we need to use subscribeOn which subscribes the observable on computation thread
        //flatMap is used to apply function on the item emitted by previous observable
        //function makes two rest service calls using the give retrofit object for defined api interface
        //these two calls run parallel that is why subscribeOn is used on each of them
        //since these two api call return same object, they are joined using concatArray operator
        //finally consumer observes on android main thread
        Observable.just(retrofit.create(StoreCouponApi.class)).subscribeOn(Schedulers.computation())
                .flatMap(s -> {
                    Observable<StoreCoupons> couponsObservable
                            = s.getCoupons("topcoupons").subscribeOn(Schedulers.io());

                    Observable<StoreCoupons> storeInfoObservable
                            = s.getStoreInfo().subscribeOn(Schedulers.io());

                    return Observable.concatArray(couponsObservable,storeInfoObservable);
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse, this::handleError );

    }
    //single api call using retrofit and rxjava
    public void getcouponData(){
        retrofit.create(StoreCouponApi.class).getCoupons("topcoupons")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError );
    }

    @Override
    public void handleResponse(StoreCoupons storeCoupons) {
        if(storeCoupons.getCoupons() != null){
            CouponsAdapter ca = new CouponsAdapter(storeCoupons.getCoupons(), getContext());
            couponRecyclerView.setAdapter(ca);
        }else{
            TextView store_name = (TextView) v.findViewById(R.id.store_name);
            store_name.setText(storeCoupons.getStore());
            TextView coupon_count = (TextView)v. findViewById(R.id.coupon_count);
            coupon_count.setText(storeCoupons.getTotalCoupons());
            TextView max_cashback = (TextView)v. findViewById(R.id.max_cashback);
            max_cashback.setText(storeCoupons.getMaxCashback());
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void retrofitobject() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void showMessage(String message) {

    }

   


    public void handleError(Throwable t){
        Log.e("Observer", ""+ t.toString());
        Toast.makeText(getContext(), "ERROR IN GETTING COUPONS",Toast.LENGTH_LONG).show();
    }

    @Override
    public void attachedView2() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onItemSelected(int position) {

    }
}
