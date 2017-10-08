package com.tchallenge.titansoft.planningpoker.prsenter;

import com.tchallenge.titansoft.planningpoker.model.IRoomListDbHelper;

public class RoomFinder {
    private final IRoomListDbHelper mRoomListDbHelper;

    public RoomFinder(IRoomListDbHelper roomListDbHelper) {
        mRoomListDbHelper = roomListDbHelper;
    }

    public boolean create(String pinCode, String nickname) {
        if(isPincodeExisted(pinCode)) {
            return false;
        } else {
            mRoomListDbHelper.joinRoom(pinCode, nickname);
            return true;
        }
    }

    private boolean isPincodeExisted(String pinCode) {
        return new PincodeChecker(mRoomListDbHelper.getExistedRooms()).hasPincode(pinCode);
    }

}
