package com.cogoport.MvpContract;

import android.view.View;

import com.cogoport.model.MainCategoryData;
import com.cogoport.model.Vikashumain;

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
        void showCoupons(View v);
        void showCouponsTopStore(View v);
        void hideProgress();
        void getStoreCouponData();
        void getcouponData();
        void retrofitobject();
        void showMessage(String message);
        void handleResponse(Vikashumain storeCoupons);
        void handleError(Throwable error);

    }

}
