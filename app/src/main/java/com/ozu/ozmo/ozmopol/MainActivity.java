package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        //this.setDrawerBackgroundColor(Color.parseColor("#F2E8D7"));
        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(),"Amin Dorostanian","amin@correctan.com",R.drawable.photo, R.drawable.bamboo);
        this.addAccount(account);
        this.addSection(newSection("Front Page", new FragmentFrontPage()));
//        this.addDivisor();
//        this.addSection(newSection("Rooms",new FrontPageFragment()).setSectionColor(Color.parseColor("#000000")));
//        this.addSection(newSection("Section 3",R.drawable.ic_mic_white_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
//        this.addSection(newSection("Section",R.drawable.ic_hotel_grey600_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));
//        // create bottom section
//        this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
//        // add pattern
//        this.setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
