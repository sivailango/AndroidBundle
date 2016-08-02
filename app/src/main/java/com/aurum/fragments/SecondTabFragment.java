package com.aurum.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aurum.R;

public class SecondTabFragment extends Fragment {

    public SecondTabFragment() {
        // Required empty public constructor
    }

    public static SecondTabFragment newInstance() {
        SecondTabFragment fragment = new SecondTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "Second Tab", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("TAG", "Second");
        return inflater.inflate(R.layout.fragment_first_tab, container, false);
    }
}
