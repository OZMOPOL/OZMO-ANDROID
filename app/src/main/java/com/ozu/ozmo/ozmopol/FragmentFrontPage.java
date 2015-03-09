package com.ozu.ozmo.ozmopol;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.view.MaterialListView;


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

        MaterialListView mListView = (MaterialListView) getView().findViewById(R.id.material_listview);

        SmallImageCard card = new SmallImageCard(getActivity());
        card.setDescription("HEY");
        card.setTitle("Title");
        card.setDrawable(R.drawable.ic_launcher);

        mListView.add(card);

    }
}

