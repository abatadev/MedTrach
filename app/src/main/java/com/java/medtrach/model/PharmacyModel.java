package com.java.medtrach.model;

public class PharmacyModel {
    private String pharmacyId, pharmacyName, pharmacyLocation, pharmacyLocationX, pharmacyLocationY;

    public PharmacyModel() {
    }

    public PharmacyModel(String pharmacyId, String pharmacyName, String pharmacyLocation, String pharmacyLocationX, String pharmacyLocationY) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmacyLocation = pharmacyLocation;
        this.pharmacyLocationX = pharmacyLocationX;
        this.pharmacyLocationY = pharmacyLocationY;
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
}
