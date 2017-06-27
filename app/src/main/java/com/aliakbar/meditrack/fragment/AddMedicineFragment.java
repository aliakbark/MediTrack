package com.aliakbar.meditrack.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.manager.ObjectFactory;
import com.aliakbar.meditrack.model.MedicineList;
import com.aliakbar.meditrack.model.User;
import com.aliakbar.meditrack.utils.BaseFragment;
import com.aliakbar.meditrack.utils.Constants;
import com.aliakbar.meditrack.utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.aliakbar.meditrack.R.id.age;


public class AddMedicineFragment extends BaseFragment implements View.OnClickListener {

    View rootView;
    Fragment fragment;

    TextInputEditText medicine_name;
    TextInputEditText dosage;
    TextInputEditText quantity;
    TextInputEditText no_of_dose_per_day;
    TextInputEditText no_of_medicines_purchased;
    AppCompatTextView add_reminder;
    AppCompatButton btn_add_medicine;

    FirebaseDatabase database;
    DatabaseReference mMedListRef;

    String deviceID = "";

    public AddMedicineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_medicine, container, false);
        deviceID = Utils.getDeviceId(getContext());
        initViews();
        viewClickListeners();

        return rootView;
    }

    private void initViews() {


        medicine_name = (TextInputEditText) rootView.findViewById(R.id.et_medicine_name);
        dosage = (TextInputEditText) rootView.findViewById(R.id.et_dosage);
        quantity = (TextInputEditText) rootView.findViewById(R.id.et_quantity);
        no_of_dose_per_day = (TextInputEditText) rootView.findViewById(R.id.et_no_of_dose_per_day);
        no_of_medicines_purchased = (TextInputEditText) rootView.findViewById(R.id.et_no_of_medicines_purchased);
        add_reminder = (AppCompatTextView) rootView.findViewById(R.id.add_reminder);
        btn_add_medicine = (AppCompatButton) rootView.findViewById(R.id.btn_add_medicine);

        database = FirebaseDatabase.getInstance();
        if (TextUtils.isEmpty(deviceID)) {
            deviceID = Utils.getDeviceId(getContext());
        }
        mMedListRef = database.getReference(Constants.USERS).child(deviceID).child(Constants.MEDICINES);


    }

    private void viewClickListeners() {
        btn_add_medicine.setOnClickListener(this);
        add_reminder.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_medicine:
                addMedicine();
                break;
            case R.id.add_reminder:
                break;
            default:
                break;
        }

    }

    private void addMedicine() {

        final String inputMedicineName = medicine_name.getText().toString();
        final String inputDosage = dosage.getText().toString().trim();
        final String inputQuantity = quantity.getText().toString().trim();
        final String inputNoOfDosePerday = no_of_dose_per_day.getText().toString().trim();
        final String inputNoOfMedicinesPurchased = no_of_medicines_purchased.getText().toString().trim();


        ObjectFactory.getInstance().getAppPreference(getContext()).setIsFirstLogIn(true);
        saveMedicineDetails(inputMedicineName, inputDosage, inputQuantity, inputNoOfDosePerday, inputNoOfMedicinesPurchased);
        Toast.makeText(getContext(), "New medicine added.", Toast.LENGTH_SHORT).show();
        medListIntent();

    }

    private void saveMedicineDetails(String medicine_name, String dosage, String quantity, String no_of_dose_per_day, String no_of_medicines_purchased) {

        MedicineList medicineList = new MedicineList(medicine_name, dosage, quantity, no_of_dose_per_day, no_of_medicines_purchased);
        mMedListRef.push().setValue(medicineList);

    }

    private void medListIntent() {
        fragment = new MedicineListFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();

    }
}
