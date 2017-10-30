package com.tchallenge.titansoft.planningpoker.model;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tchallenge.titansoft.planningpoker.R;
import com.tchallenge.titansoft.planningpoker.view.SelectCardActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDbHelper {
    private final IRoomDbListener mRoomDbListener;
    private final String mPincode;
    private DatabaseReference mRoomDbRef;

    public interface IRoomDbListener {
        void onMembesUpdate(Map<String, String> memberInfos);
        void onStartChange();
    }

    public RoomDbHelper(String pincode, IRoomDbListener listener) {
        mPincode = pincode;
        mRoomDbListener = listener;
        mRoomDbRef = FirebaseDatabase.getInstance().getReference("Rooms");
    }

    public void initRoom() {
        mRoomDbRef.child(mPincode).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Show all nickname on screen
                Map<String, String> nicknames = new HashMap<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.getKey().equals("-start-")) {
                        mRoomDbListener.onStartChange();
                    } else {
                        nicknames.put(data.getKey(), String.valueOf(data.getValue()));
                    }

                }

                mRoomDbListener.onMembesUpdate(nicknames);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void notifyStart() {
        mRoomDbRef.child(mPincode).child("-start-").setValue(-1);
    }

    public void removeUser(String nickname) {
        mRoomDbRef.child(mPincode).child(nickname).removeValue();
    }

    public void removeRoom() {
        mRoomDbRef.child(mPincode).removeValue();
    }

}