package com.java.medtrach.ui.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.java.medtrach.R;
import com.java.medtrach.common.Common;
import com.java.medtrach.common.ValidatePharmacyInput;
import com.java.medtrach.model.PharmacyModel;

public class AddPharmacyActivity extends AppCompatActivity {

    private EditText pharmacyNameEditText, pharmacyLocationEditText;
    private Button submitButton, openGoogleMaps;

    private PharmacyModel pharmacyModel;

    private String pharmacyName, pharmacyLocation;
    
    private ValidatePharmacyInput validatePharmacyInput;

    private FirebaseDatabase mDatabase;
    private DatabaseReference pharmacyReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy);
        initializeContent();
        
        validatePharmacyInput = new ValidatePharmacyInput(AddPharmacyActivity.this,
                pharmacyNameEditText, pharmacyLocationEditText);

        openGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean verifiedPharmacyName = validatePharmacyInput.validatePharmacyName();
                boolean verifiedPharmacyLocation = validatePharmacyInput.validatePharmacyLocation();

                pharmacyName = pharmacyNameEditText.getText().toString();
                pharmacyLocation = pharmacyLocationEditText.getText().toString();

                if(verifiedPharmacyName && verifiedPharmacyLocation) {
                    Intent intent = new Intent(AddPharmacyActivity.this, MapsPharmacyActivity.class);
                    intent.putExtra("pharmacyName", pharmacyName);
                    intent.putExtra("pharmacyLocation", pharmacyLocation);
                    startActivity(intent);
                }
            }
        });
    }

    private void initializeContent() {
        pharmacyNameEditText = findViewById(R.id.add_pharmacy_name_edit_text);
        pharmacyLocationEditText = findViewById(R.id.add_pharmacy_location_edit_text);
        openGoogleMaps = findViewById(R.id.pharmacy_open_google_maps_button);

        pharmacyReference = FirebaseDatabase.getInstance().getReference().child(Common.PHARMACY_REF);
    }

}