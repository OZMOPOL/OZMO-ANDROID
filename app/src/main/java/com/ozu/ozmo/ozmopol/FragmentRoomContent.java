package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;

public class FragmentRoomContent extends Fragment {
    StaggeredGridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> myCards=new ArrayList<String>();

        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");
        myCards.add("HEy");

        PostsAdapter pAdapter = new PostsAdapter(getActivity(), myCards, this.getFragmentManager());

        gridView = (StaggeredGridView)getView().findViewById(R.id.grid_view);
        gridView.setAdapter(pAdapter);
        updateColumnCountForFrontPage();
        implementCreatePostButtonFunctions();

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

    public void implementCreatePostButtonFunctions(){
        Button createPostButton = (Button)gridView.findViewById(R.id.create_a_post);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("this button", "works");
            }
        });
    }

}
