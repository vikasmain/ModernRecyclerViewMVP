package com.cogoport.api;


import com.cogoport.model.StoreCoupons;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StoreCouponApi {
    @GET("coupons/")
    Observable<StoreCoupons> getCoupons(@Query("status") String status);
    @GET("storeOffers/")
    Observable<StoreCoupons> getStoreInfo();
}