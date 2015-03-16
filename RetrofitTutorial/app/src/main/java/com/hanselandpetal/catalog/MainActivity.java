package com.hanselandpetal.catalog;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hanselandpetal.catalog.model.Flower;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ListActivity {

	public static final String PHOTOS_BASE_URL = "http://services.hanselandpetal.com/photos/";
    public static final String ENDPOINT = "http://services.hanselandpetal.com";

	TextView output;
	ProgressBar pb;
	List<Flower> flowerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    //This method will initiate the data extraction process if the internet is available.

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_get_data) {
			if (isOnline()) {
				requestData();
			} else {
				Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
			}
		}
		return false;
	}

    //The requestData method is capable of handling all of the necessary async. tasks.
    //Simply calling the RestAdapter and then calling the API will handle what's necessary.
    //For requesting data, it appears that the first two lines are essential.

	private void requestData() {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();
        FlowersAPI api = adapter.create(FlowersAPI.class);
        api.getFeed(new Callback<List<Flower>>() {
            @Override
            public void success(List<Flower> flowers, Response response) {
                Log.d("success", "data received");
                flowerList = flowers;
                updateDisplay();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", "data not received");
            }
        });
	}

	protected void updateDisplay() {
		//Use FlowerAdapter to display data
		FlowerAdapter adapter = new FlowerAdapter(this, R.layout.item_flower, flowerList);
		setListAdapter(adapter);
	}

	protected boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            Log.d("online", "online");
            return true;
		} else {
            Log.d("not_online", "not online");
			return false;
		}
	}
}