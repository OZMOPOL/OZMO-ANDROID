package com.ozu.ozmo.ozmopol.Models;

import java.util.List;

/**
 * Created by amind on 3/23/15.
 */
public class Post {
    public String pkPostId;
    public String postContent;
    public String postTitle;
    public String voteCount;
    public String postUserName;
    public List<Post> comments;
}
