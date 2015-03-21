package com.ozu.ozmo.ozmopol.Models;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by amind on 3/21/15.
 */
public interface OzmoService {
    @GET("/posts/{id}/frontpage")
    void getFrontPagePosts(@Path("id") int id, Callback<Post> cb);
}
