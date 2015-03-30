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

    @GET("/Ozmopol/webresources/entities.post/getFrontPage")
    void getFrontPagePosts(Callback<List<Post>> cb);

    @GET("/Ozmopol/webresources/entities.post/getRoomContents/{id}")
    void getRoomContents(@Path("id") String roomId,Callback<List<Post>> cb);


    @GET("/Ozmopol/webresources/entities.post/getPostContents/{id}")
    void getPostContents(@Path("id") String postId,Callback<Post> cb);


//    @Headers("Accept: application/json")
//    @GET("/Ozmopol/webresources/entities.post")
//    void getPosts(Callback<List<Post>> cb);
//
//
    @Headers("Accept: application/json")
    @GET("/Ozmopol/webresources/entities.room")
    void getRooms(Callback<List<Room>> cb);

//    @Headers("Accept: application/json")
//    @GET("/ozmoPol_WebSrv/resources/ozmopol.post")
//    void getPosts(Callback<List<Post>> cb);
}
