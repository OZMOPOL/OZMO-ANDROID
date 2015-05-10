package com.ozu.ozmo.ozmopol;

import android.app.Application;

import com.ozu.ozmo.ozmopol.Models.OzmoService;
import com.ozu.ozmo.ozmopol.Models.Room;
import com.ozu.ozmo.ozmopol.Models.User;

import retrofit.RestAdapter;

/**
 * Created by amind on 3/30/15.
 */
public class MyApplication extends Application {

    public User user;
    public Room selectedRoom;
    public String selectedPostId;
    private OzmoService ozmoService;
    public OzmoService getOzmoService()
    {
        OzmoService service;
        if (this.ozmoService==null) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.28:8080").build();
            service = restAdapter.create(OzmoService.class);
            this.ozmoService=service;
        }else{
            service=ozmoService;
        }
        return service;

    }
}