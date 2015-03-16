package com.hanselandpetal.catalog;

import com.hanselandpetal.catalog.model.Flower;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by da0517 on 15.3.2015.
 */

//This is where the Retrofit comes in.
//Such methods like @POST or @DELETE can also be defined if necessary.

public interface FlowersAPI {

    @GET("/feeds/flowers.json")
    public void getFeed(Callback<List<Flower>> response);

}
