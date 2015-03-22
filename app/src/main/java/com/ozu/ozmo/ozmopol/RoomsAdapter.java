package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amind on 3/13/15.
 */

public class RoomsAdapter extends BaseAdapter {

    static class RoomCardViewHolder {
        TextView roomTitle;
        TextView roomDescription;
        Button followButton;
    }

    private Activity mContext;
    private List<String> mList;
    private LayoutInflater mLayoutInflater = null;
    private FragmentManager fragmentManager;

    public RoomsAdapter(Activity context, List<String> list, FragmentManager fm) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fragmentManager = fm;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RoomCardViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.room_item, parent, false);
            vh = new RoomCardViewHolder();
            vh.roomTitle = (TextView) convertView.findViewById(R.id.room_title);
            vh.roomDescription = (TextView) convertView.findViewById(R.id.room_description);
            vh.followButton = (Button) convertView.findViewById(R.id.follow_room_button);
            setRoomTitleFunctions(vh.roomTitle);
            setRoomDescription(vh.roomDescription);
            setFollowButtonFunction(vh.followButton);
            convertView.setTag(vh);
        } else {
            vh = (RoomCardViewHolder) convertView.getTag();
        }
        double positionHeight = 50;
        return convertView;
    }

    public void setRoomTitleFunctions(TextView tw){
        String title = "Room title"; //obtain from server
        tw.setText(title + "");
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("room title is", "clicked");
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.grid_view, new FragmentRoomContent()); //PROBLEM HERE
                transaction.commit();
            }
        });
    }

    public void setRoomDescription(TextView tw){
        String text = "b"; //get from server
        tw.setText(text + "");
    }

    public void setFollowButtonFunction(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("room", "followed");
            }
        });
    }

}
