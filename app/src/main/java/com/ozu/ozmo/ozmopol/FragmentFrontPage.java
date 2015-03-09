package com.ozu.ozmo.ozmopol;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;


/**
 * Created by neokree on 24/11/14.
 */
public class FragmentFrontPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Create a Card
        //Card card =(Card) getView().findViewById(R.id.carddemo);


        return inflater.inflate(R.layout.my_fragment, container, false);



    }

}
