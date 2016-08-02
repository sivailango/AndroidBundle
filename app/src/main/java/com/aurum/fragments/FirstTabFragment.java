package com.aurum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aurum.R;

public class FirstTabFragment extends Fragment {

    public FirstTabFragment() {
        // Required empty public constructor
    }

    public static FirstTabFragment newInstance() {
        FirstTabFragment fragment = new FirstTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "First Tab", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("TAG", "First");
        return inflater.inflate(R.layout.fragment_first_tab, container, false);
    }
}
