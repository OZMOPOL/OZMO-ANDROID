package com.example.ugur.customexpandibleview;

/**
 * Created by Ugur on 06.04.2015.
 */
import java.util.List;

public class Post {

    public String pkPostId;
    //public String postContent;
    public String postTitle;
    //public String voteCount;
    //public String postUserName;
    public List<Post> comments;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public List<Post> getComments() {
        return comments;
}

    public void setComments(List<Post> comments) {
        this.comments = comments;
    }



    public String getPkPostId() {
        return pkPostId;
    }

    public void setPkPostId(String pkPostId) {
        this.pkPostId = pkPostId;
    }

}
