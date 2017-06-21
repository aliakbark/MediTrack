package com.aliakbar.meditrack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccountFragment extends Fragment {

    View rootView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mUserRef;


    public UserAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_account, container, false);
        initViews();
        viewClickListeners();


        return rootView;
    }

    private void initViews() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        mUserRef = firebaseDatabase.getReference(Constants.USERS);
    }

    private void viewClickListeners() {

    }

}
