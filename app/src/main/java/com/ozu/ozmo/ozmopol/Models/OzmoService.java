package com.ozu.ozmo.ozmopol.Models;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by amind on 3/21/15.
 */

public interface OzmoService {
    public static final String base_url="http://10.100.92.30:8080/";
    static final String details_url="ozmoPol_WS_GF3/resources/";

    static final String post_url="entities.post/";
    static final String vote_url="entities.vote/";
    static final String room_url="entities.room/";
    static final String user_url="entities.user/";

    @POST(details_url + post_url + "getFrontPage")
    void getFrontPagePosts(@Body User user, Callback<List<Post>> cb);

    @GET(details_url+ post_url +"getRoomContents/{id}/user/{userId}")
    void getRoomContents(@Path("id") String roomId,@Path("userId") String userId,Callback<List<Post>> cb);


    @GET(details_url+ post_url +"getPostContents/{id}")
    void getPostContents(@Path("id") String postId,Callback<Post> cb);

    @GET(details_url+ post_url +"checkLogin/{user}/pass/{pass}")
    void checkLogin(@Path("user") String user,@Path("pass") String pass, Callback<Result> cb);

    @GET(details_url+ post_url +"getUserByUserName/{userName}")
    void getUserByUserName(@Path("userName") String userName, Callback<User> cb);

    @POST(details_url+ post_url +"createPost")
    void createPost(@Body Post post, Callback<Result> cb);


    @POST(details_url + vote_url +"createVote")
    void createVote(@Body Vote vote, Callback<Result> cb);

    @PUT(details_url + vote_url + "editVote")
    void editVote(@Body Vote vote, Callback<Result> cb);

    @DELETE(details_url+ vote_url + "{id}")
    void removeVote(@Path("id") String id, Callback<Result> cb);

    @POST(details_url + user_url + "signUpVal/newUser")
    void signUp(@Body User user, Callback<Result> cb);


    @GET(details_url+"entities.room")
    void getRooms(Callback<List<Room>> cb);

    
}
