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
    public OzmoService ozmoService;

    public OzmoService ozmoService(){
        OzmoService service;
        if (ozmoService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(OzmoService.base_url).build();
            ozmoService = restAdapter.create(OzmoService.class);
        } else {

        }
        return ozmoService;

    }

    /*

    THE OLD STATE OF THE CLASS IS AS IT CAN BE SEEN BELOW. SEE ISSUE #15.

    public class MyApplication extends Application {

    public User user;
    public Room selectedRoom;
    public String selectedPostId;
    private OzmoService ozmoService;
    public OzmoService ozmoService()
    {
        OzmoService service;
        if (this.ozmoService!=null) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(OzmoService.base_url).build();
            service = restAdapter.create(OzmoService.class);
            this.ozmoService=service;
        }else{
            service=ozmoService;
        }
        return service;

    }
}
     */
}