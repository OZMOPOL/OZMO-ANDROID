package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amind on 3/9/15.
 */

public class PostsAdapter extends BaseAdapter {

    static class PostCardViewHolder {
         TextView tv;
    }

    private Activity mContext;
    private List<String> mList;
    private LayoutInflater mLayoutInflater = null;

    public PostsAdapter(Activity context, List<String> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostCardViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.post_item, parent, false);
            vh = new PostCardViewHolder();
            //vh.tv = (TextView) convertView.findViewById(R.id.tv_test);
            convertView.setTag(vh);
        } else {
            vh = (PostCardViewHolder) convertView.getTag();
        }

        double positionHeight = 50;

        //vh.tv.setText("WOOOSSS UP ?");

        return convertView;
    }

    //added on 17.03.2015 - 16.59
    //where should those functions be run at, in order to
    //give functionality to the elements of
    //each post at the front page?

    public void setVoteUpButton(){
        ImageButton voteUp = (ImageButton)getItem(R.id.tv_vote_up);
        voteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setVoteDownButton(){
        ImageButton voteDown = (ImageButton)getItem(R.id.tv_vote_down);
        voteDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setGoToPostButton(){
        ImageButton go2post = (ImageButton)getItem(R.id.tv_go2post);
        go2post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setVoteCount(){
        int votes = 0; //the info. for the votes is to be obtained from the server.
        TextView voteCount = (TextView)getItem(R.id.tv_votes);
        voteCount.setText(votes + "");
    }

    public void setPostTitle(){
        String title = "a"; //the info. for the post is to be obtained from the server.
        TextView postTitle = (TextView)getItem(R.id.tv_post_title);
        postTitle.setText(title + "");
    }

    public void setPostText(){
        String text = "a"; //the info. for the post is to be obtained from the server.
        TextView postText = (TextView)getItem(R.id.tv_post_content);
        postText.setText(text + "");
    }

}
