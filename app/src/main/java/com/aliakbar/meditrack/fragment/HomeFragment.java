package com.aliakbar.meditrack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.utils.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    View rootView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_today_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        initViews();
        viewClickListeners();

        return rootView;
    }

    private void initViews() {

    }

    private void viewClickListeners() {

    }

}
