package com.cogoport.presenter;

import com.cogoport.model.LoaderListener;
import com.cogoport.model.Picture;
import com.cogoport.view.MvpViewApi;

import java.util.ArrayList;


public class PicturePresenterImpl implements Presenter<MvpViewApi>,LoaderListener {

    private MvpViewApi pictureMvpView2;







    @Override
    public void attachedView2(MvpViewApi view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        pictureMvpView2 = view;
    }

    @Override public void detachView() {
        pictureMvpView2 = null;
    }

    @Override public void onResume() {
        pictureMvpView2.showProgress();
    }

    @Override public void onItemSelected(int position) {
        pictureMvpView2.showMessage(Integer.toString(position));

    }

    @Override public void onFinished(ArrayList<Picture> pictureList) {
        pictureMvpView2.preparealbums();
        pictureMvpView2.hideProgress();
    }
}
