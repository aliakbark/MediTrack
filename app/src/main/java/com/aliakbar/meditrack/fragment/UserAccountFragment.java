package com.aliakbar.meditrack.fragment;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.utils.BaseFragment;
import com.aliakbar.meditrack.utils.Constants;
import com.aliakbar.meditrack.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccountFragment extends BaseFragment implements View.OnClickListener {

    View rootView;
    Fragment fragment;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mUserRef;

    private AppCompatEditText user_name;
    private AppCompatEditText user_age;
    private AppCompatImageView edit_profile;
    private AppCompatTextView edit_profile_done;

    boolean edit = false;

    CardView add_medicine;
    CardView sos_settings;

    String deviceID = "";

    public UserAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_account, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        initViews();
        viewClickListeners();


        return rootView;
    }

    private void initViews() {

        deviceID = Utils.getDeviceId(getContext());

        firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            firebaseDatabase.setPersistenceEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUserRef = firebaseDatabase.getReference(Constants.USERS);
        mUserRef.keepSynced(true);

        user_name = (AppCompatEditText) rootView.findViewById(R.id.user_name);
        user_age = (AppCompatEditText) rootView.findViewById(R.id.user_age);
        edit_profile = (AppCompatImageView) rootView.findViewById(R.id.edit_profile);
        edit_profile_done = (AppCompatTextView) rootView.findViewById(R.id.edit_profile_done);
        add_medicine = (CardView) rootView.findViewById(R.id.cv_add_medicine);
        sos_settings = (CardView) rootView.findViewById(R.id.cv_sos_settings);

        mUserRef.child(deviceID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    user_name.setText(dataSnapshot.child(Constants.NAME).getValue().toString());
                    user_age.setText(dataSnapshot.child(Constants.AGE).getValue().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        setEnableFields(false);
    }

    private void viewClickListeners() {
        user_name.setOnClickListener(this);
        user_age.setOnClickListener(this);
        edit_profile.setOnClickListener(this);
        edit_profile_done.setOnClickListener(this);
        add_medicine.setOnClickListener(this);
        sos_settings.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_profile:
                edit_profile.setVisibility(View.GONE);
                edit_profile_done.setVisibility(View.VISIBLE);
                setEnableFields(true);
                edit = true;
                break;
            case R.id.edit_profile_done:
                updateUserProfile();
                break;
            case R.id.cv_add_medicine:
                intentAddMedicineFrag();
                break;
            case R.id.cv_sos_settings:
                intentSosSettingsFrag();
                break;
            default:
                break;
        }

    }

    private void setEnableFields(boolean value) {
        user_name.setEnabled(value);
        user_age.setEnabled(value);
    }

    private void updateUserProfile() {
        if (TextUtils.isEmpty(user_name.getText().toString().trim()) && TextUtils.isEmpty(user_age.getText().toString().trim())) {
            Toast.makeText(getActivity(), "Some fields are empty", Toast.LENGTH_SHORT).show();
        } else {

            mUserRef.child(deviceID).child(Constants.NAME).setValue(user_name.getText().toString());
            mUserRef.child(deviceID).child(Constants.AGE).setValue(user_age.getText().toString().trim());
            edit = false;
            edit_profile_done.setVisibility(View.GONE);
            edit_profile.setVisibility(View.VISIBLE);
            Snackbar snackbar = Snackbar.make(rootView, "Profile Updated", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        setEnableFields(false);
    }

    private void intentSosSettingsFrag() {
        fragment = new SosSettingsFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    private void intentAddMedicineFrag() {
        fragment = new AddMedicineFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }
}
