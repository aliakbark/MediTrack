package com.aliakbar.meditrack.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.adapter.MedicineListAdapter;
import com.aliakbar.meditrack.manager.ObjectFactory;
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
public class MedicineListFragment extends BaseFragment implements View.OnClickListener, MedicineListAdapter.MyClickListener {


    View rootView;

    private RecyclerView rv_medicine_list;
    private MedicineListAdapter medicineListAdapter;
    private ArrayList<MedicineList> medicineListArrayList = null;
    private ArrayList<MedicineList> serchResultLIst;

    FirebaseDatabase database;
    DatabaseReference mMedListRef;

    SearchView search_view_medicines;
    TextView empty_list;

    String deviceID = "";

    public MedicineListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_medicine_list, container, false);
        deviceID = Utils.getDeviceId(getContext());
        initViews();
        viewClickListners();

        return rootView;
    }


    private void initViews() {

        Intent intentRes = new Intent(ObjectFactory.BROADCAST_RESPONSE);
        intentRes.putExtra(ObjectFactory.BROADCAST_RESPONSE_STATUS, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intentRes);

//        search_view_medicines = (SearchView) rootView.findViewById(R.id.search_view_medicines);
        empty_list = (TextView) rootView.findViewById(R.id.empty_list);
        search_view_medicines = (SearchView) getActivity().findViewById(R.id.search_view_medicines);
        search_view_medicines.setActivated(true);
        search_view_medicines.setQueryHint("Type medicine name here");
        search_view_medicines.onActionViewExpanded();
        search_view_medicines.setIconified(false);
        search_view_medicines.clearFocus();

        medicineListArrayList = new ArrayList<MedicineList>();

        rv_medicine_list = (RecyclerView) rootView.findViewById(R.id.rv_medicine_list);
        medicineListAdapter = new MedicineListAdapter(getActivity(), new ArrayList<MedicineList>());
        rv_medicine_list.setAdapter(medicineListAdapter);

        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_medicine_list.setLayoutManager(verticalLayoutmanager);
        rv_medicine_list.setHasFixedSize(true);

        try {
            database.setPersistenceEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        database = FirebaseDatabase.getInstance();
        if (TextUtils.isEmpty(deviceID)) {
            deviceID = Utils.getDeviceId(getContext());
        }
        mMedListRef = database.getReference(Constants.USERS).child(deviceID).child(Constants.MEDICINES);

        mMedListRef.keepSynced(true);

        loadMedList();
        searchMedicine();

    }

    private void viewClickListners() {

        medicineListAdapter.setOnItemClickListener(this);

    }

    private void loadMedList() {
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
                if (medicineListArrayList.size() == 0) {
                    listEmpty();
                } else if (medicineListArrayList.size() > 0) {
                    enableList();
                    medicineListAdapter.setUpdatedMedList(medicineListArrayList);
                    medicineListAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void enableList() {
        rv_medicine_list.setVisibility(View.VISIBLE);
        empty_list.setVisibility(View.GONE);
    }

    private void listEmpty() {
        rv_medicine_list.setVisibility(View.GONE);
        empty_list.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(int position, View v) {
        switch (v.getId()) {
            case R.id.delete_medicine:
                Log.d("Cart Detail:", "cart clicked" + position);
                delete_medicine(position);
                break;
            default:
                break;
        }

    }

    private void delete_medicine(int position) {
        ArrayList<MedicineList> medListings_temp = medicineListArrayList;
        mMedListRef.removeValue();
        medListings_temp.remove(position);
        for (int i = 0; i < medListings_temp.size(); i++) {
            mMedListRef.child("" + i).setValue(medListings_temp.get(i));
        }
    }

    private void searchMedicine() {
        search_view_medicines.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                serchResultLIst = new ArrayList<MedicineList>();
                if (medicineListArrayList.size() > 0) {
                    for (int i = 0; i < medicineListArrayList.size(); i++) {
                        try {
                            String s = medicineListArrayList.get(i).getMedicine_name().toLowerCase() + " " + medicineListArrayList.get(i).getMedicine_name().toUpperCase();
                            if (s.contains(query)) {
                                serchResultLIst.add(medicineListArrayList.get(i));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (serchResultLIst.size() > 0) {
                        medicineListAdapter.setUpdatedMedList(serchResultLIst);
                        medicineListAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Nothing matched", Toast.LENGTH_SHORT).show();
                        serchResultLIst.clear();
                        medicineListAdapter.setUpdatedMedList(serchResultLIst);
                        medicineListAdapter.notifyDataSetChanged();
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                serchResultLIst = new ArrayList<MedicineList>();
                if (medicineListArrayList.size() > 0) {
                    for (int i = 0; i < medicineListArrayList.size(); i++) {
                        try {
                            String s = medicineListArrayList.get(i).getMedicine_name().toLowerCase() + " " + medicineListArrayList.get(i).getMedicine_name().toUpperCase();
                            if (s.contains(query)) {
                                serchResultLIst.add(medicineListArrayList.get(i));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (serchResultLIst.size() > 0) {
                        medicineListAdapter.setUpdatedMedList(serchResultLIst);
                        medicineListAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Nothing matched", Toast.LENGTH_SHORT).show();
                        serchResultLIst.clear();
                        medicineListAdapter.setUpdatedMedList(serchResultLIst);
                        medicineListAdapter.notifyDataSetChanged();
                    }
                }
                return false;
            }
        });
    }
}
