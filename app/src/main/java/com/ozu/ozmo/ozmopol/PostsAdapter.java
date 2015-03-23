package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ozu.ozmo.ozmopol.Models.Post;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by amind on 3/9/15.
 */

public class PostsAdapter extends BaseAdapter {

    int votes; //the vote count to be received from the server

    static class PostCardViewHolder {
        TextView voteCount, postTitle, postContent;
        ImageButton voteUpButton, voteDownButton, goToPostButton;


    }

    private Activity mContext;
    private List<Post> mList;
    private LayoutInflater mLayoutInflater = null;
    private FragmentManager fragmentManager;

    public PostsAdapter(Activity context, List<Post> list, FragmentManager fm) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fragmentManager = fm;
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
            vh.postTitle = (TextView)convertView.findViewById(R.id.tv_post_title);
            vh.postContent = (TextView)convertView.findViewById(R.id.tv_post_content);

            vh.postTitle.setText(mList.get(position).postTitle);
            vh.postContent.setText(mList.get(position).postContent);


//            vh.voteCount = (TextView)convertView.findViewById(R.id.tv_votes);
//            vh.voteUpButton = (ImageButton)convertView.findViewById(R.id.tv_vote_up);
//            vh.voteDownButton = (ImageButton)convertView.findViewById(R.id.tv_vote_down);
//            vh.goToPostButton = (ImageButton)convertView.findViewById(R.id.tv_go2post);
//            setVoteUpButton(vh.voteUpButton);
//            setVoteDownButton(vh.voteDownButton);
//            setGoToPostButton(vh.goToPostButton);
//            setVoteCount(vh.voteCount);
//            setPostTitle(vh.postTitle);
//            setPostText(vh.postContent);
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
//
//    public void setVoteUpButton(ImageButton imageButton){
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                votes++;
//            }
//        });
//    }
//
//    //User's vote value up/down is set
//    public void setVoteDownButton(ImageButton imageButton){
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                votes--;
//            }
//        });
//    }
//    //the info. for post is transfered to server
//    public void setGoToPostButton(ImageButton imageButton){
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                //TWO PROBLEMS HERE
//                //1- The problem about the usage of FragmentTransaction
//                //2- Do we have an activity/fragment that actually displays the posts with all the
//                //replies, etc that were given to it? (like a counterpart of FragmentRoomContent -
//                //the RoomContentAdapter or smth?
//                /*transaction.replace(R.id.grid_view, new FragmentRoomContent());*/
//                transaction.commit();
//            }
//        });
//    }
//
//    public void setVoteCount(TextView textView){
//        votes = 0; //the info. for the votes is to be obtained from the server.
//        textView.setText(votes + "");
//    }
//
//    public void setPostTitle(TextView textView){
//        String title = "a"; //the info. for the post is to be obtained from the server.
//        textView.setText(title + "");
//    }
//
//    public void setPostText(TextView textView){
//        String text = "a"; //the info. for the post is to be obtained from the server.
//        textView.setText(text + "");
//    }

}
