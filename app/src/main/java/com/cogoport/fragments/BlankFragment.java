package com.cogoport.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cogoport.R;
import com.cogoport.adapter.RepoAdapter;
import com.cogoport.api.ApiServiceMain;
import com.cogoport.model.MainCategoryData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cogoport.fragments.RxjavaRetrofit.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.re1)
    RecyclerView recyclerView;
    @BindView(R.id.re2)
    RecyclerView recyclerView2;

    View v;
    List<MainCategoryData> list,list2;
    private RepoAdapter adapter;
    Retrofit retrofit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this,v);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager2);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(ApiServiceMain.class).maincategorya()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleRespons, this:: handleError);
        Observable.just(retrofit.create(ApiServiceMain.class)).subscribeOn(Schedulers.computation())
                .flatMap(s -> {
                    Observable<List<MainCategoryData>> couponsObservable
                            = s.maincategorya().subscribeOn(Schedulers.io());

                    Observable<List<MainCategoryData>> storeInfoObservable
                            = s.maincategoryap().subscribeOn(Schedulers.io());

                    return Observable.merge(couponsObservable,storeInfoObservable);
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleRespons, this::handleError );

        return v;
    }




    private void handleRespons(List<MainCategoryData> storeCoupons) {

        list = new ArrayList<>(storeCoupons);
        Log.d("hello",list.toString());
        adapter = new RepoAdapter(list);
        recyclerView.setAdapter(adapter);
        list2 = new ArrayList<>(storeCoupons);//error
        adapter = new RepoAdapter(list2);
        recyclerView2.setAdapter(adapter);


    }
    public void handleError(Throwable error) {
        Toast.makeText(getContext(), "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
    }

