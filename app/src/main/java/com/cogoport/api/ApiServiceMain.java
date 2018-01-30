package com.cogoport.api;


import com.cogoport.model.MainCategoryData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiServiceMain
{
//we used post here becz here we have parameters .Get and Post works in the same way but post has parameters and get doesn't have.
    @GET("users/vikashumain/starred")
    Observable<List<MainCategoryData>> maincategoryapi();



}