package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ozu.ozmo.ozmopol.Models.OzmoService;
import com.ozu.ozmo.ozmopol.Models.Post;
import com.ozu.ozmo.ozmopol.Models.Result;
import com.ozu.ozmo.ozmopol.Models.Vote;

import org.w3c.dom.Text;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by amind on 3/9/15.
 */

public class PostsAdapter extends BaseAdapter {

    int votes; //the vote count to be received from the server

    static class PostCardViewHolder {
        TextView voteCount, postTitle, postContent,postUserName,postCDate,room;
        ImageButton voteUpButton, voteDownButton, goToPostButton;
        LinearLayout votesLayer;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        PostCardViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.post_item, parent, false);
            vh = new PostCardViewHolder();
            vh.postTitle = (TextView)convertView.findViewById(R.id.tv_post_title);
            vh.postContent = (TextView)convertView.findViewById(R.id.tv_post_content);
            vh.voteCount= (TextView)convertView.findViewById(R.id.tv_votes);
            vh.postUserName = (TextView) convertView.findViewById(R.id.tv_user);
            vh.postCDate = (TextView) convertView.findViewById(R.id.tv_createDate);
            vh.goToPostButton=(ImageButton) convertView.findViewById(R.id.tv_go2post);
            vh.votesLayer=(LinearLayout)convertView.findViewById(R.id.votes_layout);
            vh.voteDownButton=(ImageButton)convertView.findViewById(R.id.tv_vote_down);
            vh.voteUpButton=(ImageButton)convertView.findViewById(R.id.tv_vote_up);
            vh.room=(TextView)convertView.findViewById(R.id.tv_room);



            final Post post=mList.get(position);
            vh.postTitle.setText(post.postTitle);
            vh.postContent.setText(post.postContent);
            vh.voteCount.setText(post.voteCount);
            vh.postUserName.setText(post.fkPostUserId.userName);
            vh.postCDate.setText(post.postCDate);
            vh.room.setText(post.fkPostRoomId.pkRoomId);

            final Vote vote = post.vote;
            if(vote!=null){
                if (vote.voteValue){
                    vh.voteUpButton.setBackgroundColor(Color.parseColor("#A4E786"));

                }else{
                    vh.voteDownButton.setBackgroundColor(Color.parseColor("#FF4981"));
                }
            }

            vh.voteUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vote==null)
                         upVote(post);
                    else if (vote.voteValue == false)
                        editUpVote(post,vote);
                    else
                        editUnVote(post,vote);


                }
            });
            vh.voteDownButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vote==null)
                        downVote(post);
                    else if (vote.voteValue==true)
                        editDownVote(post,vote);
                    else
                        editUnVote(post,vote);
                }
            });
            if (post.postTitle==null){
                vh.votesLayer.setVisibility(View.GONE);
                vh.goToPostButton.setVisibility(View.GONE);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mList.get(position).postTitle==null)
                        return;
                   ((MyApplication) mContext.getApplication()).selectedPostId=mList.get(position).pkPostId;

                    // Create new fragment and transaction
                    Fragment newFragment = new FragmentPostContent();
                    FragmentTransaction transaction =mContext.getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack("FragmentFrontPage");

                    // Commit the transaction
                    transaction.commit();
                }
            });

            convertView.setTag(vh);
        } else {
            vh = (PostCardViewHolder) convertView.getTag();
        }


        return convertView;
    }
    void refreshPage(){
        Fragment newFragment = new FragmentFrontPage();
        FragmentTransaction transaction =mContext.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    void upVote(Post post){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
        OzmoService service = restAdapter.create(OzmoService.class);
        Vote myVote=new Vote();
        myVote.voteValue=true;
        myVote.fkVotePostId=post;
        myVote.fkVoteUserId=((MyApplication)mContext.getApplication()).user;
        RandomString randomString=new RandomString(30);
        myVote.pkVoteId=randomString.nextString();

        service.createVote(myVote,new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result.title.equalsIgnoreCase("OK")){
                    Toast.makeText(mContext.getApplicationContext(), "Your vote posted successfully !", Toast.LENGTH_SHORT).show();
                    refreshPage();
                }else{
                    Toast.makeText(mContext.getApplicationContext(), result.details, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext.getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    void downVote(Post post){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
        OzmoService service = restAdapter.create(OzmoService.class);
        Vote myVote=new Vote();
        myVote.voteValue=false;
        myVote.fkVotePostId=post;
        myVote.fkVoteUserId=((MyApplication)mContext.getApplication()).user;
        RandomString randomString=new RandomString(30);
        myVote.pkVoteId=randomString.nextString();

        service.createVote(myVote,new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result.title.equalsIgnoreCase("OK")){
                    Toast.makeText(mContext.getApplicationContext(), "Your vote posted successfully !", Toast.LENGTH_SHORT).show();
                    refreshPage();

                }else{
                    Toast.makeText(mContext.getApplicationContext(), result.details, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext.getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    void editUpVote(Post post,Vote myVote){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
        OzmoService service = restAdapter.create(OzmoService.class);

        myVote.voteValue=true;
        myVote.fkVoteUserId=((MyApplication)mContext.getApplication()).user;

        service.editVote(myVote,new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result.title.equalsIgnoreCase("OK")){
                    Toast.makeText(mContext.getApplicationContext(), "Your vote edited successfully !", Toast.LENGTH_SHORT).show();
                    refreshPage();

                }else{
                    Toast.makeText(mContext.getApplicationContext(), result.details, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext.getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void editDownVote(Post post,Vote myVote){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
        OzmoService service = restAdapter.create(OzmoService.class);

        myVote.voteValue=false;
        myVote.fkVoteUserId=((MyApplication)mContext.getApplication()).user;

        service.editVote(myVote,new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result.title.equalsIgnoreCase("OK")){
                    Toast.makeText(mContext.getApplicationContext(), "Your vote edited successfully !", Toast.LENGTH_SHORT).show();
                    refreshPage();

                }else{
                    Toast.makeText(mContext.getApplicationContext(), result.details, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext.getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void editUnVote(Post post,Vote myVote){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
        OzmoService service = restAdapter.create(OzmoService.class);

        myVote.fkVoteUserId=((MyApplication)mContext.getApplication()).user;

        service.removeVote(myVote.pkVoteId,new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result.title.equalsIgnoreCase("OK")){
                    Toast.makeText(mContext.getApplicationContext(), "Your vote removed !", Toast.LENGTH_SHORT).show();
                    refreshPage();

                }else{
                    Toast.makeText(mContext.getApplicationContext(), result.details, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext.getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });

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
