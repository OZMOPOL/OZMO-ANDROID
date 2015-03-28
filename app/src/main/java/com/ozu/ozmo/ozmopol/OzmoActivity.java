


package com.ozu.ozmo.ozmopol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.octicons_typeface_library.Octicons;


public class OzmoActivity extends ActionBarActivity {
    private static final int PROFILE_SETTING = 1;
    //save our header or result
    private AccountHeader.Result headerResult = null;
    private Drawer.Result result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozmo);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("Amin Dorostanian").withEmail("amin.dorost@gmail.com");

        // Create the AccountHeader
        headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.profile_background)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                       // new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
//                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                    @Override
//                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
//                        //sample usage of the onProfileChanged listener
//                        //if the clicked item has the identifier 1 add a new profile ;)
//                        //false if you have not consumed the event and it should close the drawer
//                        return false;
//                    }
//                })
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Notifications").withIcon(GoogleMaterial.Icon.gmd_wb_sunny).withIdentifier(1).withCheckable(false),
                        new PrimaryDrawerItem().withName("Front Page").withIcon(FontAwesome.Icon.faw_home).withIdentifier(2).withCheckable(false),
                        new PrimaryDrawerItem().withName("Rooms").withIcon(FontAwesome.Icon.faw_ticket).withIdentifier(3).withCheckable(false),
                        new DividerDrawerItem(),
                        new SwitchDrawerItem().withName("Notifications").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                Log.i("material-drawer", "toggleChecked: " + isChecked);
                            }
                        })
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

//                        if (drawerItem != null) {
//                            if (drawerItem.getIdentifier() == 1) {
//                                Intent intent = new Intent(SimpleHeaderDrawerActivity.this, SimpleCompactHeaderDrawerActivity.class);
//                                SimpleHeaderDrawerActivity.this.startActivity(intent);
//                            } else if (drawerItem.getIdentifier() == 2) {
//                                Intent intent = new Intent(SimpleHeaderDrawerActivity.this, ActionBarDrawerActivity.class);
//                                SimpleHeaderDrawerActivity.this.startActivity(intent);
//                            } else if (drawerItem.getIdentifier() == 3) {
//                                Intent intent = new Intent(SimpleHeaderDrawerActivity.this, MultiDrawerActivity.class);
//                                SimpleHeaderDrawerActivity.this.startActivity(intent);
//                            } else if (drawerItem.getIdentifier() == 4) {
//                                Intent intent = new Intent(SimpleHeaderDrawerActivity.this, SimpleNonTranslucentDrawerActivity.class);
//                                SimpleHeaderDrawerActivity.this.startActivity(intent);
//                            } else if (drawerItem.getIdentifier() == 5) {
//                                Intent intent = new Intent(SimpleHeaderDrawerActivity.this, ComplexHeaderDrawerActivity.class);
//                                SimpleHeaderDrawerActivity.this.startActivity(intent);
//                            } else if (drawerItem.getIdentifier() == 6) {
//                                Intent intent = new Intent(SimpleHeaderDrawerActivity.this, SimpleFragmentDrawerActivity.class);
//                                SimpleHeaderDrawerActivity.this.startActivity(intent);
//                            } else if (drawerItem.getIdentifier() == 20) {
//                                new Libs.Builder().withFields(R.string.class.getFields()).withActivityTheme(R.style.MaterialDrawerTheme_ActionBar).start(SimpleHeaderDrawerActivity.this);
//                            }
//                        }
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 10
            result.setSelectionByIdentifier(10, false);

            //set the active profile
            headerResult.setActiveProfile(profile);
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ozmo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
