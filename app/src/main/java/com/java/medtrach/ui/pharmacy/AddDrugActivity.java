package com.java.medtrach.ui.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.java.medtrach.common.ValidateDrugInput;
import com.java.medtrach.model.DrugModel;

public class AddDrugActivity extends AppCompatActivity {

    private DatabaseReference drugReference;
    private FirebaseDatabase mDatabase;
    
    ValidateDrugInput validateDrugInput;
    DrugModel drugModel;

    EditText drugNameEditText, drugDescriptionEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);
        initializeContent();

        validateDrugInput = new ValidateDrugInput(
                AddDrugActivity.this, drugNameEditText, drugDescriptionEditText
        );
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitToFirebase();
            }
        });
    }

    private void initializeContent() {
        drugNameEditText = findViewById(R.id.add_drug_drug_name_edit_text);
        drugDescriptionEditText = findViewById(R.id.add_drug_description_edit_text);
        submitButton = findViewById(R.id.add_drug_submit_button);

        drugReference = FirebaseDatabase.getInstance().getReference().child(Common.DRUG_REF);
    }

    private void submitToFirebase() {
        final String drugId = drugReference.push().getKey();

        String drugName = drugNameEditText.getText().toString().trim();
        String drugDescription = drugDescriptionEditText.getText().toString().trim();

        boolean drugNameVerified = validateDrugInput.validateDrugName();
        
        if(drugNameVerified) {
            drugModel = new DrugModel();

            drugModel.setDrugId(drugId);
            drugModel.setDrugName(drugName);
            drugModel.setDrugDescription(drugDescription);

            drugReference.child(drugId).setValue(drugModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddDrugActivity.this, "Added entry.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddDrugActivity.this, "Failed to add entry.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
        }

        
    }


}