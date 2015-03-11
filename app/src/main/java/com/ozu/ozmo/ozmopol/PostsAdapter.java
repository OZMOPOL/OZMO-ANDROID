package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amind on 3/9/15.
 */
public class PostsAdapter extends BaseAdapter {

    static class PostCardViewHolder {
         TextView tv;
    }
    private Activity mContext;
    private List<String> mList;
    private LayoutInflater mLayoutInflater = null;
    public PostsAdapter(Activity context, List<String> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        PostCardViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.post_item, parent, false);
            vh = new PostCardViewHolder();
            //vh.tv = (TextView) convertView.findViewById(R.id.tv_test);
            convertView.setTag(vh);
        } else {
            vh = (PostCardViewHolder) convertView.getTag();
        }

        double positionHeight = 50;

        //vh.tv.setText("WOOOSSS UP ?");

        return convertView;
    }
}
