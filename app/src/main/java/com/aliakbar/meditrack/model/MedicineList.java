package com.aliakbar.meditrack.model;

/**
 * Created by ALIAKBAR on 22-06-2017.
 */

public class MedicineList {

    public String medicine_name;
    public String dasage;
    public String quantity;
    public String no_of_dose_per_day;
    public String no_of_medicines_purchased;

    public MedicineList() {
    }

    public MedicineList(String medicine_name, String dasage, String quantity, String no_of_dose_per_day, String no_of_medicines_purchased) {
        this.medicine_name = medicine_name;
        this.dasage = dasage;
        this.quantity = quantity;
        this.no_of_dose_per_day = no_of_dose_per_day;
        this.no_of_medicines_purchased = no_of_medicines_purchased;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getDasage() {
        return dasage;
    }

    public void setDasage(String dasage) {
        this.dasage = dasage;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNo_of_dose_per_day() {
        return no_of_dose_per_day;
    }

    public void setNo_of_dose_per_day(String no_of_dose_per_day) {
        this.no_of_dose_per_day = no_of_dose_per_day;
    }

    public String getNo_of_medicines_purchased() {
        return no_of_medicines_purchased;
    }

    public void setNo_of_medicines_purchased(String no_of_medicines_purchased) {
        this.no_of_medicines_purchased = no_of_medicines_purchased;
    }
}
