package com.java.medtrach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.java.medtrach.common.Common;

public class LandingPageActivity extends AppCompatActivity {
    final String TAG = LandingPageActivity.class.getSimpleName();
    private DatabaseReference roleRef;

    private int roleType;
    private String userId, roleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        roleRef = FirebaseDatabase.getInstance().getReference(Common.ROLE_REF);

        String userId = getIntent().getStringExtra("userId");

        roleRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    try {
                        int roleType = snapshot.child("roleType").getValue(int.class);

                        switch(roleType) {
                            case Common.ROLE_ADMIN:
                                Intent intentAdmin = new Intent(LandingPageActivity.this, HomeActivity.class);
                                startActivity(intentAdmin);
                                break;
                            case Common.ROLE_USER:
                                Intent intentUser = new Intent(LandingPageActivity.this, HomeActivity.class);
                                startActivity(intentUser);
                                break;
                            default:
                                Log.d(TAG, "onDataChange: Triggered default case.");
                                break;
                        }
                    } catch (NullPointerException e ) {
                        Log.d(TAG, "onDataChange: " + e.getMessage().toString());
                    }
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}