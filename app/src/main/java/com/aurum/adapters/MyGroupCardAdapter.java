package com.aurum.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aurum.R;
import com.aurum.models.MyContact;
import com.aurum.models.MyContactCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/3/16.
 */

/*
public class MyGroupCardAdapter extends BaseAdapter {

    private Context context;
    private List<MyContact> contactCardList = new ArrayList<MyContact>();
    private int mGroupPosition;

    public MyGroupCardAdapter(Context context, List<MyContact> cards, int mGroupPosition) {
        this.context = context;
        this.contactCardList = cards;
        this.mGroupPosition = mGroupPosition;
    }

    @Override
    public int getCount() {
        return contactCardList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactCardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_card_child, null, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TextView sharedBalAmount = (TextView) convertView.findViewById(R.id.text_card_name);



        sharedBalAmount.setText("Hello");


        return convertView;


        //return convertView;
    }

    private static class ViewHolder {
        TextView cardName;
    }
}

*/

public class MyGroupCardAdapter extends RecyclerView.Adapter<MyGroupCardAdapter.ViewHolder> {

    private Context context;
    private List<MyContactCard> contactCardList = new ArrayList<MyContactCard>();

    public MyGroupCardAdapter(Context context, List<MyContactCard> dataSet) {
        this.context = context;
        this.contactCardList = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cardView = inflater.inflate(R.layout.item_card_child, null, false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        viewHolder.cardName = (TextView) cardView.findViewById(R.id.text_card_name);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView itemTextView = (TextView) holder.cardName;
        itemTextView.setText(contactCardList.get(position).cardName);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return contactCardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        TextView cardName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardName = (TextView) itemView.findViewById(R.id.text_card_name);
        }
    }

}
