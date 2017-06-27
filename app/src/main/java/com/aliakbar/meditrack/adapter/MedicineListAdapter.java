package com.aliakbar.meditrack.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.model.MedicineList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALIAKBAR on 21-06-2017.
 */

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.DataObjectHolder> {

    ArrayList<MedicineList> medicineLists;
    public Context context;

    public MedicineListAdapter(Context context, ArrayList<MedicineList> medicineLists) {
        this.medicineLists = medicineLists;
        this.context = context;
    }

    @Override
    public MedicineListAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_list_item, parent, false);
        MedicineListAdapter.DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MedicineListAdapter.DataObjectHolder holder, int position) {
        if (medicineLists.size() > 0) {
            holder.medicine_name.setText(medicineLists.get(position).getMedicine_name());
        } else {
            holder.medicine_name.setText("Adol650");
        }


    }

    @Override
    public int getItemCount() {
        return medicineLists.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView medicine_name;
        public AppCompatImageView delete_medicine;

        public DataObjectHolder(View itemView) {
            super(itemView);

            medicine_name = (AppCompatTextView) itemView.findViewById(R.id.medicine_name);
            delete_medicine = (AppCompatImageView) itemView.findViewById(R.id.delete_medicine);
        }
    }

    public void setUpdatedMedList(ArrayList<MedicineList> list) {
        this.medicineLists = list;
    }


}
