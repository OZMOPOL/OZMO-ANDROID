package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
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
        View v = convertView;
        PostCardViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.layout, null);
            viewHolder = new PostCardViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (PostCardViewHolder) v.getTag();
        }
        TextView tv=new TextView(mContext);
        tv.setText("This is a test");
        //viewHolder.mCardView.addView(tv);
        return v;
    }
}
class PostCardViewHolder {
    public CardView mCardView;
    public PostCardViewHolder(View base) {
        mCardView = (CardView) base.findViewById(R.id.cardview);
    }
}