package com.cogoport.MvpContract;

import com.cogoport.model.MainCategoryData;

import java.util.List;

/**
 * Created by dell on 01-02-2018.
 */

public interface RxjavaRetrofitcontract {
    interface MvpViewApi {
        void preparealbums();

        void showProgress();

        void hideProgress();

        void showMessage(String message);
        void setupViews();
    }
    interface MvpModelApi{
        String getSong();
        void setSong(String song);
        String getUrl();
        void setUrl(String url);
        String getArtists();
        void setArtists(String artists);
        String getCover_image();
        void setCover_image(String cover_image);
    }
    interface MvpPresenterApi<M> {
        void attachedView2();
        void detachView();

        void onResume();
        void onItemClickListener(int position);

        void onItemSelected(int position);
    }
    interface MvpViewRxjava {
        void showProgress();

        void hideProgress();

        void showMessage(String message);
        void load();
        void initRecyclerView();
        void handleResponse(List<MainCategoryData> androidList);
        void handleError(Throwable error);
    }
    interface MultipleMvpViewRxjava {
        void showProgress();

        void hideProgress();

        void initRecycler();
        void loadData();
        void handleResponseLeft(List<MainCategoryData> storeCoupons);
        void handleResponseRight(List<MainCategoryData> storeCoupons);
        void showMessage(String message);
        void retrofitObject();
        void handleError(Throwable error);

    }

}
