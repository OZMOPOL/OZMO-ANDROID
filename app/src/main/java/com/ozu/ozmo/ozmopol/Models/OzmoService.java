package com.ozu.ozmo.ozmopol.Models;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by amind on 3/21/15.
 */
public interface OzmoService {

//    @GET("/posts/{id}/frontpage")
//    void getFrontPagePosts(@Path("id") int id, Callback<Post> cb);

    @Headers("Accept: application/json")
    @GET("/Ozmopol/webresources/entities.post")
    void getPosts(Callback<List<Post>> cb);




//    @Headers("Accept: application/json")
//    @GET("/ozmoPol_WebSrv/resources/ozmopol.post")
//    void getPosts(Callback<List<Post>> cb);
}
