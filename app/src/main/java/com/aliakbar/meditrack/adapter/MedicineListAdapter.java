package com.aliakbar.meditrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliakbar.meditrack.R;

/**
 * Created by ALIAKBAR on 21-06-2017.
 */

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.DataObjectHolder> {

    @Override
    public MedicineListAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_list_item, parent, false);
        MedicineListAdapter.DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MedicineListAdapter.DataObjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        public DataObjectHolder(View itemView) {
            super(itemView);
        }
    }
}
