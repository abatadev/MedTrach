package com.java.medtrach.model;

import java.util.List;

public class PharmacyModel {
    private String pharmacyId, pharmacyName, pharmacyLocation, pharmacyLocationX, pharmacyLocationY;

    List<DrugModel> drugs;

    public PharmacyModel() {
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyLocation() {
        return pharmacyLocation;
    }

    public void setPharmacyLocation(String pharmacyLocation) {
        this.pharmacyLocation = pharmacyLocation;
    }

    public String getPharmacyLocationX() {
        return pharmacyLocationX;
    }

    public void setPharmacyLocationX(String pharmacyLocationX) {
        this.pharmacyLocationX = pharmacyLocationX;
    }

    public String getPharmacyLocationY() {
        return pharmacyLocationY;
    }

    public void setPharmacyLocationY(String pharmacyLocationY) {
        this.pharmacyLocationY = pharmacyLocationY;
    }

    public List<DrugModel> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugModel> drugs) {
        this.drugs = drugs;
    }
}
