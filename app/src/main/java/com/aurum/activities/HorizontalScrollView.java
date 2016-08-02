package com.aurum.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.aurum.R;
import com.aurum.adapters.MyGroupAdapter;
import com.aurum.models.MyContact;
import com.aurum.models.MyContactCard;

import java.util.ArrayList;
import java.util.List;

public class HorizontalScrollView extends AppCompatActivity {

    private ExpandableListView mMyContactsListView;
    private ExpandableListAdapter mAdapter;
    private List<MyContact> myContacts = new ArrayList<MyContact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);

        ExpandableListView mMyContactsListView = (ExpandableListView) findViewById(R.id.my_contacts);

        data();

        mAdapter = new MyGroupAdapter(this, myContacts);
        mMyContactsListView.setAdapter(mAdapter);


    }

    private void data() {
        MyContact myContact1 = new MyContact();
        myContact1.name = "Siva";

        MyContact myContact2 = new MyContact();
        myContact2.name = "Ilango";

        List<MyContactCard> cards1 = new ArrayList<MyContactCard>();
        cards1.add(new MyContactCard("Amazon"));
        cards1.add(new MyContactCard("KFC"));
        cards1.add(new MyContactCard("Evergreen"));
        cards1.add(new MyContactCard("Starbucks"));
        cards1.add(new MyContactCard("Evergreen"));
        cards1.add(new MyContactCard("Starbucks"));
        cards1.add(new MyContactCard("Evergreen"));
        cards1.add(new MyContactCard("Starbucks"));
        cards1.add(new MyContactCard("Evergreen"));
        cards1.add(new MyContactCard("Starbucks"));

        myContact1.cards = cards1;

        List<MyContactCard> cards2 = new ArrayList<MyContactCard>();
        cards2.add(new MyContactCard("AAA"));
        cards2.add(new MyContactCard("BBB"));
        cards1.add(new MyContactCard("CCC"));
        cards2.add(new MyContactCard("DDD"));
        cards2.add(new MyContactCard("EEE"));
        cards2.add(new MyContactCard("FFF"));
        cards2.add(new MyContactCard("GGG"));
        cards2.add(new MyContactCard("HHH"));
        cards2.add(new MyContactCard("III"));
        cards2.add(new MyContactCard("JJJ"));

        myContact2.cards = cards2;

        myContacts.add(myContact1);
        myContacts.add(myContact2);
    }
}
