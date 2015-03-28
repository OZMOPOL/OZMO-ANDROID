package com.ozu.ozmo.ozmopol;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;
import com.ozu.ozmo.ozmopol.Models.Contributor;
import com.ozu.ozmo.ozmopol.Models.OzmoService;
import com.ozu.ozmo.ozmopol.Models.Post;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;




public class FragmentFrontPage extends Fragment {

    StaggeredGridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_front_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://10.100.92.21:8080/")
                .build();

        OzmoService service = restAdapter.create(OzmoService.class);

        final List<Post> myPostCards=new ArrayList<Post>();

        service.getPosts(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                for (int i=0;i<posts.size();i++){
                    if (posts.get(i).postTitle !=null ){
                        myPostCards.add(posts.get(i));
                    }
                }

                PostsAdapter pAdapter=new PostsAdapter(getActivity(),myPostCards, FragmentFrontPage.this.getFragmentManager());

                gridView = (StaggeredGridView)getView().findViewById(R.id.grid_view);
                gridView.setAdapter(pAdapter);
                updateColumnCountForFrontPage();

                ProgressWheel progressWheel=(ProgressWheel)getActivity().findViewById(R.id.progress_wheel);
                progressWheel.setVisibility(View.GONE);
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

