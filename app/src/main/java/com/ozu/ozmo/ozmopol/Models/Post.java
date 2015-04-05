package com.ozu.ozmo.ozmopol.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amind on 3/23/15.
 */
public class Post {



    public String pkPostId;
    public String postContent;
    public String postTitle;
    public String postCDate;
    public String postEDate;

    public Post fkPostPrntId;
    public Room fkPostRoomId;
    public User fkPostUserId;




    public List<Post> comments;
    public String voteCount;

    public Vote vote;
}

