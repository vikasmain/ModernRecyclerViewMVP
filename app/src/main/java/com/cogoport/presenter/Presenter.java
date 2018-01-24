package com.cogoport.presenter;


public interface Presenter <V> {

    void attachedView2(V view);
    void detachView();

    void onResume();

    void onItemSelected(int position);
}
