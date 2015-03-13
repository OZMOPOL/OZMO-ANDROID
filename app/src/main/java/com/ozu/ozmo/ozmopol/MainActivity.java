package com.ozu.ozmo.ozmopol;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;


public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        //this.setDrawerBackgroundColor(Color.parseColor("#F2E8D7"));
        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(),"Amin Dorostanian","amin@correctan.com",R.drawable.amind, R.drawable.profile_background);
        this.addAccount(account);

        MaterialSection section_notifications=newSection("Notifications", new FragmentFrontPage());
        //  section_front_page.setSectionColor(Color.parseColor("#000000"));
        // section_front_page.setPressingColor(Color.parseColor("#c0c0c0"));

        this.addSection(section_notifications);


        MaterialSection section_front_page=newSection("Front Page", new FragmentFrontPage());
      //  section_front_page.setSectionColor(Color.parseColor("#000000"));
      // section_front_page.setPressingColor(Color.parseColor("#c0c0c0"));

        this.addSection(section_front_page);


        MaterialSection section_rooms=newSection("Rooms",new FragmentRooms());
      //  section_rooms.setSectionColor(Color.parseColor("#000000"));
      //  section_rooms.setPressingColor(Color.parseColor("#c0c0c0"));
       // section_rooms.setColorSelected(Color.parseColor("#000000"));
        this.addSection(section_rooms);

        MaterialSection section_settings=newSection("Settings",new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection materialSection) {

            }
        });
       // section_notifications.setSectionColor(Color.parseColor("#000000"));
        //section_notifications.setPressingColor(Color.parseColor("#c0c0c0"));
        this.addSection(section_settings);


        this.addBottomSection(newSection("Logout",new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection materialSection) {

            }
        }));

        disableLearningPattern();
        //addMultiPaneSupport();
       // this.setDrawerBackgroundColor(Color.parseColor("#F7F5E4"));
//        this.addDivisor();
//        this.addSection(newSection("Rooms",new FrontPageFragment()).setSectionColor(Color.parseColor("#000000")));
//        this.addSection(newSection("Section 3",R.drawable.ic_mic_white_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
//        this.addSection(newSection("Section",R.drawable.ic_hotel_grey600_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));
//        // create bottom section
//        this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
//        // add pattern
//        this.setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);
    }

}
