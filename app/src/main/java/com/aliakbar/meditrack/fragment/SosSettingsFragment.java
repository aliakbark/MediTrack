package com.aliakbar.meditrack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.utils.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SosSettingsFragment extends BaseFragment {
    View rootView;


    public SosSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sos_settings, container, false);

        return rootView;
    }

}
