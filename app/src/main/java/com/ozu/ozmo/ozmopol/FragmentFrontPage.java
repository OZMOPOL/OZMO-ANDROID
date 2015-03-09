package com.ozu.ozmo.ozmopol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by neokree on 24/11/14.
 */
public class FragmentFrontPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_front_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> myCards=new ArrayList<String>();

        myCards.add("HEy");


        PostsAdapter pAdapter=new PostsAdapter(getActivity(),myCards);

        StaggeredGridView gridView = (StaggeredGridView)getView().findViewById(R.id.grid_view);

        gridView.setAdapter(pAdapter);

    }
}

