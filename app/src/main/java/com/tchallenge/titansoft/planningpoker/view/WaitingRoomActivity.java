package com.tchallenge.titansoft.planningpoker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tchallenge.titansoft.planningpoker.R;


public class WaitingRoomActivity extends AppCompatActivity {

    private DatabaseReference mRoomDbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        String pincode = getIntent().getStringExtra("pincode");

        mRoomDbRef = FirebaseDatabase.getInstance().getReference("Rooms");
        mRoomDbRef.child(pincode).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Show all nickname on screen
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
