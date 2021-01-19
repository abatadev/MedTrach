package com.java.medtrach.ui.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.java.medtrach.R;
import com.java.medtrach.common.Common;
import com.java.medtrach.model.DrugModel;
import com.java.medtrach.model.PharmacyModel;


/**
 * TODO
 * 1. Set permissions
 * 2. Get current location
 * 3. Set fragment
 * 4. Add coordinates
 * 5. Pass coordinates to previous intent
 * 6. Pass intents
 * 7. Fix bugs
 */

public class MapsPharmacyActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LocationRequest locationRequest;
    private TextView pharmacyNameTextView, pharmacyLocationTextView;
    private TextView pharmacyXLongitude, pharmacyYLatitude;
    private Button submitActivityMapsButton;

    private FirebaseDatabase mDatabase;
    private DatabaseReference drugReference, pharmacyReference;

    String pharmacyName, pharmacyLocation;
    Double pharmacyLongitude, pharmacyLatitude;

    DrugModel drugModel = new DrugModel();
    PharmacyModel pharmacyModel = new PharmacyModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_pharmacy);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.pharmacy_map_fragment);
        mapFragment.getMapAsync(MapsPharmacyActivity.this::onMapReady);
        buildLocationRequest();

        pharmacyNameTextView = findViewById(R.id.card_view_pharmacy_name_textView);
        pharmacyLocationTextView = findViewById(R.id.card_view_parmacy_location_textView);
        pharmacyXLongitude = findViewById(R.id.longitude_x_text_view);
        pharmacyYLatitude = findViewById(R.id.latitude_y_text_view);
        submitActivityMapsButton = findViewById(R.id.submit_activity_maps_button);

        mDatabase = FirebaseDatabase.getInstance();
        drugReference = mDatabase.getReference().child(Common.DRUG_REF);
        pharmacyReference = mDatabase.getReference().child(Common.PHARMACY_REF);

        Intent intent = getIntent();
        pharmacyName = intent.getStringExtra("pharmacyName");
        pharmacyLocation = intent.getStringExtra("pharmacyLocation");

        pharmacyNameTextView.setText(pharmacyName);
        pharmacyLocationTextView.setText(pharmacyLocation);

        submitActivityMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(pharmacyLatitude.toString()) && TextUtils.isEmpty(pharmacyLongitude.toString())) {
                    Toast.makeText(MapsPharmacyActivity.this, "Tap screen to input coordinates.", Toast.LENGTH_SHORT).show();
                } else {
                    submitToFirebase();
                }

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("onMapClick", "Coordinates" + latLng.latitude + " | " + latLng.longitude);
                pharmacyXLongitude.setText(String.format("X:%s", latLng.longitude));
                pharmacyYLatitude.setText(String.format("Y: %s", latLng.latitude));

                pharmacyLatitude = latLng.latitude;
                pharmacyLongitude = latLng.longitude;
            }
        });
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(15000); //15 seconds
        locationRequest.setFastestInterval(10000); //10 seconds
        locationRequest.setSmallestDisplacement(20f); // 20 meters
    }


    private void submitToFirebase() {
        String pharmacyId = pharmacyReference.push().getKey();

        pharmacyModel.setPharmacyId(pharmacyId);
        pharmacyModel.setPharmacyName(pharmacyName);
        pharmacyModel.setPharmacyLocation(pharmacyLocation);
        pharmacyModel.setPharmacyLocationX(pharmacyLongitude);
        pharmacyModel.setPharmacyLocationY(pharmacyLatitude);

        pharmacyReference.child(pharmacyId).setValue(pharmacyModel)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MapsPharmacyActivity.this, "Registered to Database.", Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MapsPharmacyActivity.this, "Failed to register to Database.", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "E: " + e.getMessage());
            }
        });

    }


}