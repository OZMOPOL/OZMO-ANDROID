package com.ozu.ozmo.ozmopol;

import android.app.Application;

import com.ozu.ozmo.ozmopol.Models.Room;
import com.ozu.ozmo.ozmopol.Models.User;

/**
 * Created by amind on 3/30/15.
 */
public class MyApplication extends Application {

    public User user;
    public Room selectedRoom;
    public String selectedPostId;
}