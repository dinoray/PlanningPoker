package com.tchallenge.titansoft.planningpoker.prsenter;

import com.tchallenge.titansoft.planningpoker.model.IRoomListDbHelper;

public class RoomFinder {
    private final IRoomListDbHelper mRoomListDbHelper;

    public RoomFinder(IRoomListDbHelper roomListDbHelper) {
        mRoomListDbHelper = roomListDbHelper;
    }

    public boolean create(String pinCode, String nickname) {
        return mRoomListDbHelper.createRoom(pinCode, nickname);
    }
}
