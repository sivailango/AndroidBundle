package com.aurum.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.aurum.R;
import com.aurum.adapters.TabAdapter;
import com.aurum.fragments.FirstTabFragment;
import com.aurum.fragments.SecondTabFragment;

public class TabActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabAdapter mTapAdapter;
    private TabLayout mTabLayout;

    private FirstTabFragment firstTabFragment;
    private SecondTabFragment secondTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mTapAdapter = new TabAdapter(getSupportFragmentManager());


        firstTabFragment = new FirstTabFragment();
        secondTabFragment = new SecondTabFragment();

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));

        setCurrentTabFragment(0);

        // mViewPager.setAdapter(mTapAdapter);
        // mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        /*

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    getLayoutInflater().inflate(R.layout.fragment_first_tab, null);
                    //mTabLayout.addView(R.id.);
                    //mViewPager.setCurrentItem(new FirstTabFragment());
                } else if (position == 1) {
                    getLayoutInflater().inflate(R.layout.fragment_second_tab, null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        */

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {



                setCurrentTabFragment(tab.getPosition());

                // mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0 :
                replaceFragment(firstTabFragment);
                break;
            case 1 :
                replaceFragment(secondTabFragment);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

}
