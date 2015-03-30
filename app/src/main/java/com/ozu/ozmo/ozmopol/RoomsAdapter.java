package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ozu.ozmo.ozmopol.Models.Room;

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
    private List<Room> mList;
    private LayoutInflater mLayoutInflater = null;
    private FragmentManager fragmentManager;

    public RoomsAdapter(Activity context, List<Room> rooms, FragmentManager fm) {
        mContext = context;
        mList = rooms;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        RoomCardViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.room_item, parent, false);
            vh = new RoomCardViewHolder();
            vh.roomTitle = (TextView) convertView.findViewById(R.id.room_title);
            vh.roomDescription = (TextView) convertView.findViewById(R.id.room_description);
            vh.followButton = (Button) convertView.findViewById(R.id.follow_room_button);

            vh.roomTitle.setText(mList.get(position).roomTitle);
            vh.roomDescription.setText(mList.get(position).roomDesc);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Room room =mList.get(position);
                    ((MyApplication) mContext.getApplication()).selectedRoomId=room.pkRoomId;

                    // Create new fragment and transaction
                    Fragment newFragment = new FragmentRoomContent();
                    FragmentTransaction transaction =mContext.getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack

                    transaction.add(R.id.fragment_container,newFragment);

                    // Commit the transaction
                    transaction.commit();


                }
            });

            convertView.setTag(vh);
        } else {
            vh = (RoomCardViewHolder) convertView.getTag();
        }
        return convertView;
    }



}
