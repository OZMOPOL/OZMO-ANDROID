package com.example.ugur.customexpandibleview;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Post> mainPosts; // header titles
    private Post mainPost; // header title
    private Post commentPost; // child data in format of header title, child title

    public ExpandableListAdapter(Context context, List<Post> mainPost) {
        this.context = context;
        this.mainPosts = mainPosts;
    }

    private String getPostTitle(Post post) {
        return post.getPostTitle();
    }
    private String getPkPostId(Post post) {
        return post.getPkPostId();
    }

    public Post getParent(List<Post> mainPosts, int postPosition) {
        return mainPosts.get(postPosition);
    }

    public Post getChild(Post mainPost, int commentPosition) {
        return mainPost.comments.get(commentPosition);
    }

    public View getChildView(Post mainPost, int commentPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getPostTitle(getChild(mainPost, commentPosition));

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    public View getGroupView(Post mainPost, int groupId, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = getPostTitle(mainPost);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
