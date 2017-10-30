package com.tchallenge.titansoft.planningpoker.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoomMemberDbHelper {

    private DatabaseReference mRoomMemberDbRef;

    public RoomMemberDbHelper(String pincode, String nickanme) {
        mRoomMemberDbRef = FirebaseDatabase.getInstance().getReference("Rooms").child(pincode).child(nickanme);
    }

    public void updateResult(String result) {
        mRoomMemberDbRef.setValue(result);
    }
}
