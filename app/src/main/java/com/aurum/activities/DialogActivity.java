package com.aurum.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aurum.R;
import com.aurum.fragments.PinDialog;
import com.aurum.listeners.PinDialogListener;

public class DialogActivity extends AppCompatActivity implements PinDialogListener {

    LinearLayout cardLayout;
    ImageView convertedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button mShowDialog = (Button) findViewById(R.id.btn_show_dialog);
        Button mAnimationActivity = (Button) findViewById(R.id.btn_animation_activity);

        cardLayout = (LinearLayout) findViewById(R.id.layout_card);
        cardLayout.setBackgroundResource(R.drawable.tags_rounded_corners);

        GradientDrawable drawable = (GradientDrawable) cardLayout.getBackground();
        drawable.setColor(getResources().getColor(R.color.accent));


        convertedImage = (ImageView) findViewById(R.id.image_converted);

        /*

        cardLayout.setDrawingCacheEnabled(true);

        cardLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        cardLayout.layout(0, 0, cardLayout.getMeasuredWidth(), cardLayout.getMeasuredHeight());

        cardLayout.buildDrawingCache(true);

        Bitmap bm = Bitmap.createBitmap(cardLayout.getDrawingCache());
        cardLayout.setDrawingCacheEnabled(false);

        */



        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                PinDialog pinDialog = PinDialog.newInstance(1);
                pinDialog.show(fm, "Sample Fragment");
            }
        });

        mAnimationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this, AnimationActivity.class));
            }
        });

    }

    /*

    public Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(cardLayout.getLayoutParams().width, cardLayout.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        view.layout(0, 0, view.getLayoutParams().width, view.getLayoutParams().height);
        view.draw(canvas);

        return returnedBitmap;
    }*/

    public void convertImage(View v) {
        Bitmap returnedBitmap = Bitmap.createBitmap(cardLayout.getWidth(), cardLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        cardLayout.layout(0, 0, cardLayout.getWidth(), cardLayout.getHeight());
        cardLayout.draw(canvas);
        convertedImage.setVisibility(View.VISIBLE);
        convertedImage.setImageBitmap(returnedBitmap);
    }

    public void pinPositiveClick() {
        Log.d("DialogActivity", "Positive");
    }

    @Override
    public void okClickListener() {

        Log.d("DIALOG", "From Actitity");
    }

}
