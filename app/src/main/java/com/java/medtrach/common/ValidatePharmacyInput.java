package com.java.medtrach.common;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class ValidatePharmacyInput {
    Context context;
    EditText pharmacyName, pharmacyLocation;

    String pharmacyNameInput, pharmacyDescriptionInput, pharmacyLocationInput;

    public ValidatePharmacyInput(Context myContext, EditText myPharmacyName) {
        context = myContext;
        pharmacyName = myPharmacyName;
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

}
