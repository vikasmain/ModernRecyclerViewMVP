package com.cogoport.view;

/**
 * Created by dell on 22-01-2018.
 */

public interface MvpViewApi {
    void preparealbums();

    void showProgress();

    void hideProgress();

    void showMessage(String message);

}
