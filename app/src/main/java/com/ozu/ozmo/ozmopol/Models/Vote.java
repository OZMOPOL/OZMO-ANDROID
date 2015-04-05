package com.ozu.ozmo.ozmopol.Models;

/**
 * Created by amind on 4/5/15.
 */
public class Vote {
    public Post fkVotePostId;
    public User fkVoteUserId;
    public String pkVoteId;
    public boolean voteValue;
}
