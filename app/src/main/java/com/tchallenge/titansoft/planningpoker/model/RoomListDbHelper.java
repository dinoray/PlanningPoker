package com.tchallenge.titansoft.planningpoker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoomListDbHelper implements IRoomListDbHelper {
    private static final String TAG = RoomListDbHelper.class.getSimpleName();

    public interface IRoomDbListener {
        void onRoomJoin(String pinCode, boolean isSuccess);
        void onRoomExit(String pinCode, boolean isSuccess);
    }

    private final String DEFAULT_POKER = "0";

    private final DatabaseReference mRoomDbRef;
    private final IRoomDbListener mRoomDbListener;

    public RoomListDbHelper(IRoomDbListener roomDbListener) {
        mRoomDbRef = FirebaseDatabase.getInstance().getReference("Rooms");
        mRoomDbListener = roomDbListener;
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
                        Log.d(TAG, "pinCode: "+pinCode+" create success");
                        mRoomDbListener.onRoomJoin(pinCode, true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "pinCode: "+pinCode+" create fail");
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
                        Log.d(TAG, "pinCode: "+pinCode+" exist success");
                        mRoomDbListener.onRoomExit(pinCode, true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "pinCode: "+pinCode+" exist fail");
                        mRoomDbListener.onRoomExit(pinCode, false);
                    }
                });
    }
}
