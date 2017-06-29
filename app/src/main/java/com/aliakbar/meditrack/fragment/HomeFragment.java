package com.aliakbar.meditrack.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.manager.ObjectFactory;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("MediTrack");

        initViews();
        viewClickListeners();

        return rootView;
    }

    private void initViews() {
        Intent intentRes = new Intent(ObjectFactory.BROADCAST_RESPONSE);
        intentRes.putExtra(ObjectFactory.BROADCAST_RESPONSE_STATUS, false );
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intentRes);
    }

    private void viewClickListeners() {

    }

}
