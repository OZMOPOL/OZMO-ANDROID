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
    public static final String base_url="http://10.100.92.28:8080";
    static final String details_url="/ozmoPol_WS_GF3/resources";

    static final String post_url="/com.ozmo.ent.ozpost";
    static final String vote_url="/com.ozmo.ent.ozvote";
    static final String room_url="/com.ozmo.ent.ozroom";
    static final String user_url="/com.ozmo.ent.ozuser";

    // Posts
    @POST(details_url + user_url + "/uList")
    void testSrv(Callback<Result> cb);


    @POST(details_url + post_url + "/getFrontPage")
    void getFrontPagePosts(@Body User user, Callback<Result> cb);

    @GET(details_url+ post_url +"/getRoomContents/{id}/user/{userId}")
    void getRoomContents(@Path("id") String roomId,@Path("userId") String userId,Callback<Result> cb);


    @GET(details_url+ post_url +"getPostContents/{id}")
    void getPostContents(@Path("id") String postId,Callback<Result> cb);


    @POST(details_url+ post_url +"/createPost")
    void createPost(@Body Post post, Callback<Result> cb);


    // Votes
    @POST(details_url + vote_url +"/createVote")
    void createVote(@Body Vote vote, Callback<Result> cb);

    @PUT(details_url + vote_url + "/editVote")
    void editVote(@Body Vote vote, Callback<Result> cb);

    @DELETE(details_url+ vote_url + "{id}")
    void removeVote(@Path("id") String id, Callback<Result> cb);

    //Users

    @POST(details_url + user_url + "/signUpVal")
    void signUp(@Body User user, Callback<Result> cb);

    @POST(details_url + user_url + "/verifyUser")
    void verifyUser(@Body User user, Callback<Result> cb);

    @POST(details_url+ user_url +"/checkLogin")
    void checkLogin(@Body User user, Callback<Result> cb);

    @GET(details_url+ user_url +"/getUserByUserName/{userName}")
    void uProfile(@Path("userName") String userName, Callback<User> cb);


    @POST(details_url+ user_url +"/sendActCode")
    void sendActCode(@Body User user,Callback<Result> cb);

    //Rooms

    @POST(details_url+ room_url + "/createRoom" )
    void createRoom(@Body Room room, Callback<Result> cb);


    @GET(details_url+ room_url )
    void getRooms(Callback<Result> cb);




}
