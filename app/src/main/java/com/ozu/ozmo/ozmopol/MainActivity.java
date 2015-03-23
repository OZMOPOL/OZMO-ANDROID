package com.ozu.ozmo.ozmopol;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;


public class MainActivity extends MaterialNavigationDrawer {

    boolean isRoom = false;

    @Override
    public void init(Bundle savedInstanceState) {
        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(), "Amin Dorostanian", "amin@correctan.com", R.drawable.amind, R.drawable.profile_background);
        this.addAccount(account);
        MaterialSection section_notifications = newSection("Notifications", new FragmentFrontPage());
        this.addSection(section_notifications);

        MaterialSection section_front_page = newSection("Front Page", new FragmentFrontPage());
        this.addSection(section_front_page);

        FragmentRooms fr_rooms_page=new FragmentRooms();
        MaterialSection section_rooms = newSection("Rooms", fr_rooms_page);
        this.addSection(section_rooms);
        MaterialSection section_settings = newSection("Settings", new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection materialSection) {

            }
        });
        this.addSection(section_settings);
        //add button functionality
        this.addBottomSection(newSection("Logout",new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection materialSection) {

            }
        }));
        disableLearningPattern();
        this.setFragment(fr_rooms_page, "TEST");

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_post:
                    this.setFragment(new CreatePostFragment(),"Create Post");

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
