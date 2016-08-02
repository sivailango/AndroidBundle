package com.aurum.adapters;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.aurum.R;
import com.aurum.models.MyContact;
import com.aurum.models.MyContactCard;

import java.util.List;

/**
 * Created by root on 1/3/16.
 */
public class MyGroupAdapter implements ExpandableListAdapter {

    private Activity activity;
    private List<MyContact> myContacts;

    public MyGroupAdapter(Activity activity, List<MyContact> myContacts) {
        this.activity = activity;
        this.myContacts = myContacts;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return myContacts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return myContacts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return myContacts.get(groupPosition).cards.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyContact sharedUsers = (MyContact) getGroup(groupPosition);

        if(convertView == null) {
            LayoutInflater userInflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            convertView = userInflater.inflate(R.layout.item_parent_group, parent, false);
        }

        TextView userList = (TextView) convertView.findViewById(R.id.text_contact);
        userList.setText(sharedUsers.name);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_group_child, parent, false);
            childHolder = new ChildHolder();

            childHolder.horizontalListView = (RecyclerView) convertView.findViewById(R.id.horizontal_cards);

            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
            childHolder.horizontalListView.setLayoutManager(layoutManager);
           // childHolder.horizontalListView.setHasFixedSize(true);


            convertView.setTag(childHolder);

        }
        else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        MyGroupCardAdapter horizontalListAdapter = new MyGroupCardAdapter(activity, myContacts.get(groupPosition).cards);
        childHolder.horizontalListView.setAdapter(horizontalListAdapter);

        return convertView;


        /*

        MyContactCard sharedCard = (MyContactCard) getChild(groupPosition, childPosition);

        if(convertView == null) {
            LayoutInflater cardInflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            convertView = cardInflater.inflate(R.layout.item_card_child, null);
            convertView.setHorizontalScrollBarEnabled(true);
        }

        TextView sharedBalAmount = (TextView) convertView.findViewById(R.id.text_card_name);



        sharedBalAmount.setText(sharedCard.cardName);


        return convertView;

        */
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private static class ChildHolder {
        static RecyclerView horizontalListView;
    }
}
