package com.cogoport.presenter;

import android.view.View;

import com.cogoport.MvpContract.RxjavaRetrofitcontract;


public class DataPresenterImpl implements RxjavaRetrofitcontract.MvpPresenterApi<View> {

    private RxjavaRetrofitcontract.MvpViewApi pictureMvpView2;









    @Override
    public void attachedView2() {
//        if (view == null)
//            throw new IllegalArgumentException("You can't set a null view");
//
//        pictureMvpView2 = view;
    }

    @Override public void detachView() {
        pictureMvpView2 = null;
    }

    @Override public void onResume() {
        pictureMvpView2.showProgress();
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override public void onItemSelected(int position) {
        pictureMvpView2.showMessage(Integer.toString(position));

    }


}
