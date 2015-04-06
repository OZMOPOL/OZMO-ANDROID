


package com.ozu.ozmo.ozmopol;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.ozu.ozmo.ozmopol.Models.OzmoService;
import com.ozu.ozmo.ozmopol.Models.Room;
import com.ozu.ozmo.ozmopol.Models.User;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class OzmoActivity extends ActionBarActivity implements FragmentPostContent.OnFragmentInteractionListener,
        CreatePostFragment.OnFragmentInteractionListener,DialogLogin.OnFragmentInteractionListener,DialogSignup.OnFragmentInteractionListener {
    public int selectedRoomIndex=0;
    private static final int PROFILE_SETTING = 1;
    Bundle savedInstanceState;
    Toolbar toolbar;
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

        this.savedInstanceState=savedInstanceState;
        this.toolbar=toolbar;
        showDrawer();




        SharedPreferences prefs = this.getSharedPreferences(
                "com.ozu.ozmo.ozmopol", Context.MODE_PRIVATE);

        boolean loggedIn= prefs.getBoolean("loggedIn",false);
        if (loggedIn){
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
            OzmoService service = restAdapter.create(OzmoService.class);
            String userName=prefs.getString("userName","");
            service.getUserByUserName(userName,new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    // Logged in
                    ((MyApplication) getApplication()).user=user;
                    showFrontPage();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), "An error occured during process !", Toast.LENGTH_SHORT).show();
                }
            });



        }else{
            getSupportActionBar().hide();
            showLoginDialog();
        }





        //((MyApplication) getApplication()).user=new User("bjksefkhjw49ub43","1200-01-01T00:00:00+02:00","jesey@ozu.edu.tr","Jesey","bjksefkhjw49ub43bjksefkhjw49ub43bjksefkhjw49ub43bjksefkhjw49ub43","1");

    }
    public void showDrawer(){
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

                        if (drawerItem != null) {
                            int selected_index=drawerItem.getIdentifier();
                            Fragment newFragment=new FragmentFrontPage();
                            if(selected_index== 2){
                                newFragment = new FragmentFrontPage();

                            }
                            else if ( selected_index== 3) {
                                newFragment = new FragmentRooms();
                            }

                            FragmentTransaction transaction = getFragmentManager().beginTransaction();

                            // Replace whatever is in the fragment_container view with this fragment,
                            // and add the transaction to the back stack
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.addToBackStack(null);

                            // Commit the transaction
                            transaction.commit();
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
                        }
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





        // Create a new Fragment to be placed in the activity layout
    }
    void showFrontPage(){
            FragmentFrontPage firstFragment = new FragmentFrontPage();
            firstFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment).commit();
    }
    void showLoginDialog() {
        // Create a new Fragment to be placed in the activity layout
        DialogLogin loginFragment = new DialogLogin();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        loginFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getFragmentManager().beginTransaction().add(R.id.fragment_container,loginFragment).commit();

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
            if (getFragmentManager().getBackStackEntryCount() > 0 ){
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
