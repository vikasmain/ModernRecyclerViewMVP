package com.cogoport.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cogoport.MvpContract.RxjavaRetrofitcontract;
import com.cogoport.R;
import com.cogoport.adapter.RepoAdapter;
import com.cogoport.api.ApiServiceMain;
import com.cogoport.model.MainCategoryData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cogoport.fragments.RxjavaRetrofit.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultipleRxjavaProcesses extends Fragment implements RxjavaRetrofitcontract.MultipleMvpViewRxjava{


    public MultipleRxjavaProcesses() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    ProgressDialog progressDialog;

    View v;
    List<MainCategoryData> list,list2;
    private RepoAdapter adapter;
    Retrofit retrofit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.re1);
        recyclerView2=(RecyclerView)v.findViewById(R.id.re2);
//        ButterKnife.bind(this,v);
        progressDialog=new ProgressDialog(getContext());
        showMessage("Loading");
        initRecycler();
        retrofitObject();
        loadData();
        return v;
    }


    public void handleResponseLeft(List<MainCategoryData> storeCoupons) {
        hideProgress();
        List<MainCategoryData> list = new ArrayList<>(storeCoupons);
        RepoAdapter adapter = new RepoAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    public void handleResponseRight(List<MainCategoryData> storeCoupons) {
        hideProgress();
        List<MainCategoryData> list2 = new ArrayList<>(storeCoupons);
        RepoAdapter adapter2 = new RepoAdapter(list2);
        recyclerView2.setAdapter(adapter2);
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void initRecycler() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager2);
    }

    @Override
    public void loadData() {
        showProgress();
        ApiServiceMain s1 = retrofit.create(ApiServiceMain.class);

        s1.maincategorya()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseLeft, this::handleError);

        s1.maincategoryap()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseRight, this::handleError);
    }

    @Override
    public void retrofitObject() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void showMessage(String message) {
      progressDialog.setMessage(message);
    }



    public void handleError(Throwable error) {
        hideProgress();
        Toast.makeText(getContext(), "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
    }

