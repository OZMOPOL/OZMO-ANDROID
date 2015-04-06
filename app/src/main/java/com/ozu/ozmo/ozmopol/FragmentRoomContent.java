
package com.ozu.ozmo.ozmopol;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.ozu.ozmo.ozmopol.Models.OzmoService;
import com.ozu.ozmo.ozmopol.Models.Post;
import com.ozu.ozmo.ozmopol.Models.Room;
import com.ozu.ozmo.ozmopol.Models.User;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentRoomContent extends Fragment {
    StaggeredGridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        Button btnCreatePost=(Button)getView().findViewById(R.id.btn_create_post);
        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new CreatePostFragment();
                FragmentTransaction transaction =getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack("FragmentRoomContent");

                // Commit the transaction
                transaction.commit();

            }
        });

        Room room=((MyApplication) getActivity().getApplication()).selectedRoom;
        User user=((MyApplication)getActivity().getApplication()).user;
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
        OzmoService service = restAdapter.create(OzmoService.class);
        final List<Post> myPostCards=new ArrayList<Post>();


        service.getRoomContents(room.pkRoomId,user.pkUserId,new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {

                 myPostCards.addAll(posts);
                 PostsAdapter pAdapter=new PostsAdapter(getActivity(),myPostCards, FragmentRoomContent.this.getFragmentManager());
                 gridView = (StaggeredGridView)getView().findViewById(R.id.room_content_grid_view);
                 gridView.setAdapter(pAdapter);
                 updateColumnCountForFrontPage();
                 ProgressWheel progressWheel=(ProgressWheel)getActivity().findViewById(R.id.room_content_progress_wheel);
                 progressWheel.setVisibility(View.GONE);
                if (posts.size()!=0){
                    TextView tvEmptyRoom=(TextView)getView().findViewById(R.id.tv_empty_room);
                    tvEmptyRoom.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });







    }

    public boolean isTablet(){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        if (width > 720 && height > 1184){
            return true;
        } else {
            return false;
        }
    }

    public void updateColumnCountForFrontPage(){
        if(isTablet()){
            Log.d("Tablet spotted", "sizes won't change");
        } else {
            gridView.setColumnCountLandscape(1);
            gridView.setColumnCountPortrait(1);
        }
    }


}
