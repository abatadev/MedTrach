package com.java.medtrach.common;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class ValidatePharmacyInput {
    Context context;
    EditText pharmacyName, pharmacyLocation;

    String pharmacyNameInput, pharmacyLocationInput;

    public ValidatePharmacyInput(Context myContext, EditText myPharmacyName, EditText myPharmacyLocation) {
        context = myContext;
        pharmacyName = myPharmacyName;
        pharmacyLocation = myPharmacyLocation;
    }

    public boolean validatePharmacyName() {
        pharmacyNameInput = pharmacyName.getText().toString();

        if(pharmacyNameInput.isEmpty()) {
            Toast.makeText(context, "Pharmacy's name cannot be empty.", Toast.LENGTH_SHORT).show();
            return  false;
        } else {
            return true;
        }
    }

    public boolean validatePharmacyLocation() {
        pharmacyLocationInput = pharmacyLocation.getText().toString();

        if(pharmacyLocationInput.isEmpty()) {
            Toast.makeText(context, "Pharmacy's location cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
