package com.aurum.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.aurum.R;
import com.aurum.fragments.PinDialog;
import com.aurum.listeners.PinDialogListener;

public class AnimationActivity extends Activity implements PinDialogListener {

    private TextView mMessageTxt;
    private Button mFadeInBtn;
    private Button mFadeOutBtn;

    Animation fadeInAnimation;
    Animation fadeOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mMessageTxt = (TextView) findViewById(R.id.txt_message);
        mFadeInBtn = (Button) findViewById(R.id.btn_fade_in);
        mFadeOutBtn = (Button) findViewById(R.id.btn_fade_out);

        Button mShowDialog = (Button) findViewById(R.id.btn_show_dialog);

        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        fadeOutAnimation  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        mFadeInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessageTxt.setVisibility(View.VISIBLE);
                mMessageTxt.startAnimation(fadeInAnimation);
            }
        });

        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                PinDialog pinDialog = PinDialog.newInstance(1);
                //PinDialog pinDialog = new PinDialog();
                //pinDialog.setTargetFragment(PinDialog.newInstance(1), 0);
                pinDialog.show(fm, "Sample Fragment");
            }
        });



    }

    @Override
    public void okClickListener() {
        Log.d("DIALOG", "From Actitity");
    }

}
