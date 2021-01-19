package com.java.medtrach.model;

import java.util.List;

public class PharmacyModel {
    private String pharmacyId, pharmacyName, pharmacyLocation;
    private Double pharmacyLocationX, pharmacyLocationY;

    List<DrugModel> drugs;

    public PharmacyModel() {
    }

    public PharmacyModel(String pharmacyId, String pharmacyName, String pharmacyLocation, Double pharmacyLocationX, Double pharmacyLocationY, List<DrugModel> drugs) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmacyLocation = pharmacyLocation;
        this.pharmacyLocationX = pharmacyLocationX;
        this.pharmacyLocationY = pharmacyLocationY;
        this.drugs = drugs;
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

    public Double getPharmacyLocationX() {
        return pharmacyLocationX;
    }

    public void setPharmacyLocationX(Double pharmacyLocationX) {
        this.pharmacyLocationX = pharmacyLocationX;
    }

    public Double getPharmacyLocationY() {
        return pharmacyLocationY;
    }

    public void setPharmacyLocationY(Double pharmacyLocationY) {
        this.pharmacyLocationY = pharmacyLocationY;
    }

    public List<DrugModel> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugModel> drugs) {
        this.drugs = drugs;
    }
}