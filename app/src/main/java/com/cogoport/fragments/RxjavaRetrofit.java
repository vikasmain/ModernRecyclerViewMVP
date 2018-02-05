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


//That is, as a developer
// you don’t have to worry too much about the details of how to perform operations that should occur on different threads.
public class RxjavaRetrofit extends Fragment implements RxjavaRetrofitcontract.MvpViewRxjava,
        RxjavaRetrofitcontract.MvpPresenterApi{

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
        load();
        return v;
    }

    public void initRecyclerView() {

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    public void load() {
showProgress();
showMessage("Loading Data");
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

    public void handleResponse(List<MainCategoryData> androidList) {
hideProgress();
list = new ArrayList<>(androidList);
        adapter = new RepoAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    public void handleError(Throwable error) {
hideProgress();
Toast.makeText(getContext(), "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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


    @Override
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
      progressDialog.hide();
    }

    @Override
    public void showMessage(String message) {
        progressDialog.setMessage(message);

    }
}
