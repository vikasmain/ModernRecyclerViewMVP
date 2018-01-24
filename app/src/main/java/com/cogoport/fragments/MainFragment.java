package com.cogoport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cogoport.R;
import com.cogoport.model.Picture;
import com.cogoport.presenter.PicturePresenterImpl;
import com.cogoport.presenter.RecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikkyb on 13/12/17.
 */
public abstract class MainFragment extends Fragment implements  RecyclerItemClickListener {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private PicturePresenterImpl picturePresenter;
    RecyclerView.Adapter adapter;

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, rootView);

        picturePresenter = new PicturePresenterImpl();
//        picturePresenter.attachedView((MvpViewApi) this);
        setupRecyclerView();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        picturePresenter.onResume();
    }

//    @Override
//    public void setItems(ArrayList<Picture> pictureList) {
//        adapter = getAdapter(pictureList);
//        recyclerView.setAdapter(adapter);
//
//        if(adapter instanceof AdapterExample)
//            ((AdapterExample) adapter).setRecyclerItemClickListener(this);
//        else if(adapter instanceof AdapterExampleTypes)
//            ((AdapterExampleTypes) adapter).setRecyclerItemClickListener(this);
//
//    }
//
//    @Override
//    public void showProgress() {
//        progressBar.setVisibility(View.VISIBLE);
//        recyclerView.setVisibility(View.INVISIBLE);
//    }
//
//    @Override
//    public void hideProgress() {
//        progressBar.setVisibility(View.INVISIBLE);
//        recyclerView.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void showMessage(String message) {
//        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onDestroy() {
        picturePresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onItemClickListener(int position) {
        picturePresenter.onItemSelected(position);
    }

    private void setupRecyclerView() {
        if(getLayoutManager() != null)
            recyclerView.setLayoutManager(getLayoutManager());
            recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    protected abstract int getLayout();
    protected abstract RecyclerView.LayoutManager getLayoutManager();
    protected abstract RecyclerView.Adapter getAdapter(ArrayList<Picture> pictureList);
}
