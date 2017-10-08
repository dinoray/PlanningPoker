package com.tchallenge.titansoft.planningpoker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomListDbHelper implements IRoomListDbHelper {
    private static final String TAG = RoomListDbHelper.class.getSimpleName();

    public interface IRoomDbListener {
        void onRoomJoin(String pinCode, boolean isSuccess);

        void onRoomExit(String pinCode, boolean isSuccess);
    }

    private final String DEFAULT_POKER = "0";

    private final DatabaseReference mRoomDbRef;
    private final IRoomDbListener mRoomDbListener;

    private List<String> mRoomPindCodeList = new ArrayList<>();

    public RoomListDbHelper(IRoomDbListener roomDbListener) {
        mRoomDbRef = FirebaseDatabase.getInstance().getReference("Rooms");
        mRoomDbListener = roomDbListener;
        initRoomList();
    }

    private void initRoomList() {
        mRoomDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mRoomPindCodeList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d(TAG, "pinCode: " + data.getKey() + " added");
                    mRoomPindCodeList.add(dataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void joinRoom(final String pinCode, String nickName) {
        mRoomDbRef
                .child(pinCode)
                .child(nickName)
                .setValue(DEFAULT_POKER)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "pinCode: " + pinCode + " create success");
                        mRoomDbListener.onRoomJoin(pinCode, true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "pinCode: " + pinCode + " create fail");
                        mRoomDbListener.onRoomJoin(pinCode, false);
                    }
                });
    }

    public void exitRoom(final String pinCode, String nickName) {
        mRoomDbRef
                .child(pinCode)
                .child(nickName)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "pinCode: " + pinCode + " exist success");
                        mRoomDbListener.onRoomExit(pinCode, true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "pinCode: " + pinCode + " exist fail");
                        mRoomDbListener.onRoomExit(pinCode, false);
                    }
                });
    }

    @Override
    public List<String> getExistedRooms() {
        return mRoomPindCodeList;
    }
}
