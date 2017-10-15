package com.tchallenge.titansoft.planningpoker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tchallenge.titansoft.planningpoker.R;


public class WaitingRoomActivity extends AppCompatActivity {

    private static final String TAG = WaitingRoomActivity.class.getSimpleName();;
    private DatabaseReference mRoomDbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        String pincode = getIntent().getStringExtra("pincode");
        boolean isHost = getIntent().getBooleanExtra("host",false);

        if (isHost){
            findViewById(R.id.btn_start).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WaitingRoomActivity.this, SelectCardActivity.class);
                    startActivity(intent);
                }
            });
        }

        mRoomDbRef = FirebaseDatabase.getInstance().getReference("Rooms");
        mRoomDbRef.child(pincode).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Show all nickname on screen
                LinearLayout layout = (LinearLayout) findViewById(R.id.layout_wait);
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    TextView tv = new TextView(WaitingRoomActivity.this);
                    tv.setText(data.getKey());
                    layout.addView(tv,0);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
