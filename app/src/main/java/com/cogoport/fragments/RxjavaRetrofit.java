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

import com.cogoport.R;
import com.cogoport.adapter.RepoAdapter;
import com.cogoport.api.ApiServiceMain;
import com.cogoport.model.MainCategoryData;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RxjavaRetrofit extends Fragment {

    @BindView(R.id.re)
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    View v;
    List<MainCategoryData> list;
    private RepoAdapter adapter;
    CompositeDisposable mcompositeDisposable;
    public RxjavaRetrofit() {
        // Required empty public constructor
    }
    public static final String BASE_URL = "https://api.github.com/";





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_rxjava_retrofit, container, false);

        progressDialog=new ProgressDialog(getContext());
        ButterKnife.bind(this,v);
        initRecyclerView();
        loadJSON();
        return v;
    }

    private void initRecyclerView() {

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        progressDialog.show();
        progressDialog.setMessage("Loading Data");
        ApiServiceMain requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServiceMain.class);
        Observable<List<MainCategoryData>> observable=requestInterface.maincategoryapi().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(this::handleResponse,this::handleError);

    }

    private void handleResponse(List<MainCategoryData> androidList) {
        progressDialog.hide();
        list = new ArrayList<>(androidList);
        adapter = new RepoAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void handleError(Throwable error) {
        progressDialog.hide();
        Toast.makeText(getContext(), "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

}
