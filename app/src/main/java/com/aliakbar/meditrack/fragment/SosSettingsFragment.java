package com.aliakbar.meditrack.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.utils.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SosSettingsFragment extends BaseFragment implements View.OnClickListener {
    View rootView;
    AppCompatButton btn_sos_update;


    public SosSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sos_settings, container, false);
        initViews();
        viewClickListeners();
        return rootView;
    }

    private void initViews() {
        btn_sos_update = (AppCompatButton) rootView.findViewById(R.id.btn_sos_update);
    }

    private void viewClickListeners() {
        btn_sos_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sos_update:
                Snackbar snackbar = Snackbar.make(rootView, "SOS Updated", Snackbar.LENGTH_LONG);
                snackbar.show();
                break;
        }
    }
}
