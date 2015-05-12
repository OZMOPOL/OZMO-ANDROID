package com.ozu.ozmo.ozmopol.Models;

import java.util.Date;
import java.util.List;

/**
 * Created by amind on 4/5/15.
 */
public class User {
    public String pkUserId;
    public String userBday;
    public String userEmail;
    public String userName;
    public String userPass;
    public Boolean userStatus;
    public String useractHash;
    //
    public List<Post> comments;
    public List<Post> posts;
    public List<User> flwdUsers;
    public List<Room> flwdRooms;
    public List<Vote> votes;
}
