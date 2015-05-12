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
    public static final String base_url="http://10.100.92.26:8080/";
    static final String details_url="/ozmoPolWS/webresources";

    static final String post_url="/com.ozmopol.ozpost";
    static final String vote_url="/com.ozmopol.ozvote";
    static final String room_url="/com.ozmopol.ozroom";
    static final String user_url="/com.ozmopol.ozuser";

    // Posts
    @POST(details_url + user_url + "/uList")
    void testSrv(Callback<Result> cb);


    @POST(details_url + user_url + "/getFrontPage")
    void getFrontPagePosts(@Body User user, Callback<Result> cb);

    @POST(details_url+ user_url +"/getRoomDetails")
    void getRoomDetail(@Body Result res, Callback<Result> cb);


    @POST(details_url+ user_url +"/getPostDetails")
    void getPostDetails(@Body Result req, Callback<Result> cb);


    @POST(details_url+ post_url +"/createPost")
    void createPost(@Body Post post, Callback<Result> cb);


    @POST(details_url+ user_url +"/getUserProfile")
    void getUserProfile(@Body User user, Callback<Result> cb);


    // Votes
    @POST(details_url + vote_url + "/createVote")
    void createVote(@Body Vote vote, Callback<Result> cb);

    @POST(details_url + vote_url + "/editVote")
    void editVote(@Body Vote vote, Callback<Result> cb);

    @POST(details_url+ vote_url  + "/deleteVote")
    void removeVote(@Body Vote vote, Callback<Result> cb);

    //Users

    @POST(details_url + user_url + "/signUp")
    void signUp(@Body User user, Callback<Result> cb);

    @POST(details_url + user_url + "/activateUser")
    void verifyUser(@Body User user, Callback<Result> cb);

    @POST(details_url+ user_url +"/logIn")
    void checkLogin(@Body User user, Callback<Result> cb);

    @GET(details_url+ user_url +"/getUserByUserName/{userName}")
    void uProfile(@Path("userName") String userName, Callback<User> cb);


    @POST(details_url+ user_url +"/sendActCode")
    void sendActCode(@Body User user,Callback<Result> cb);

    //Rooms

    @POST(details_url+ user_url +"/getRoomList")
    void getRoomList(@Body User user,Callback<Result> cb);

    @POST(details_url+ room_url + "/createRoom" )
    void createRoom(@Body Room room, Callback<Result> cb);



    @POST(details_url+ "/com.ozmopol.xuserflwroom" + "/followRoom" )
    void followRoom(@Body Xuserflwroom req, Callback<Result> cb);


    @POST(details_url+ "/com.ozmopol.xuserflwroom" + "/unfollowRoom" )
    void unfollowRoom(@Body Xuserflwroom req, Callback<Result> cb);

}
