package com.aliakbar.meditrack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.adapter.MedicineListAdapter;
import com.aliakbar.meditrack.model.MedicineList;
import com.aliakbar.meditrack.utils.BaseFragment;
import com.aliakbar.meditrack.utils.Constants;
import com.aliakbar.meditrack.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineListFragment extends BaseFragment implements View.OnClickListener {

    View rootView;

    private RecyclerView rv_medicine_list;
    private MedicineListAdapter medicineListAdapter;
    private ArrayList<MedicineList> medicineListArrayList = null;

    FirebaseDatabase database;
    DatabaseReference mUserRef;
    DatabaseReference mMedListRef;

    String deviceID = "";

    public MedicineListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_medicine_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        deviceID = Utils.getDeviceId(getContext());
        initViews();
        viewClickListners();

        return rootView;
    }

    private void initViews() {
        rv_medicine_list = (RecyclerView) rootView.findViewById(R.id.rv_medicine_list);
        medicineListAdapter = new MedicineListAdapter(getActivity(), new ArrayList<MedicineList>());
        rv_medicine_list.setAdapter(medicineListAdapter);

        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_medicine_list.setLayoutManager(verticalLayoutmanager);
        rv_medicine_list.setHasFixedSize(true);

        medicineListArrayList = new ArrayList<MedicineList>();
        database = FirebaseDatabase.getInstance();
        if (TextUtils.isEmpty(deviceID)) {
            deviceID = Utils.getDeviceId(getContext());
        }
//        mUserRef = database.getReference(Constants.USERS).child(deviceID).child(Constants.MEDICINES);
        mMedListRef = database.getReference(Constants.USERS).child(deviceID).child(Constants.MEDICINES);


        mMedListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                medicineListArrayList = new ArrayList<MedicineList>();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    MedicineList medicineList = new MedicineList();
                    medicineList.setMedicine_name(childDataSnapshot.child(Constants.MEDICINE_NAME).getValue().toString());
                    medicineList.setDosage(childDataSnapshot.child(Constants.DOSAGE).getValue().toString());
                    medicineList.setQuantity(childDataSnapshot.child(Constants.QUANTITY).getValue().toString());
                    medicineList.setNo_of_dose_per_day(childDataSnapshot.child(Constants.NO_OF_DOSE_PER_DAY).getValue().toString());
                    medicineList.setNo_of_medicines_purchased(childDataSnapshot.child(Constants.NO_OF_MEDICINES_PURCHASED).getValue().toString());
                    medicineListArrayList.add(medicineList);
                }
                medicineListAdapter.setUpdatedMedList(medicineListArrayList);

                medicineListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void viewClickListners() {

        if (medicineListAdapter != null) {

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
