package com.aurum.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.aurum.R;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        /*


        ImageView imageView = (ImageView) findViewById(R.id.image_card_1);


        Picasso.with(this).load("https://s3.amazonaws.com/cambi-staging/profile/subway-482532-131GiftCard")
                .into(imageView);

        */

        /*

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
                .build();

        Picasso.with(this)
                .load("https://s3.amazonaws.com/cambi-staging/profile/subway-482532-131GiftCard")
                .fit()
                .transform(transformation)
                .into(imageView);

                */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_built_in_tab, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}
