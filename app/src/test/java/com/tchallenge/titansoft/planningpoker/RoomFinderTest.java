package com.tchallenge.titansoft.planningpoker;

import android.support.annotation.NonNull;

import com.tchallenge.titansoft.planningpoker.model.IRoomListDbHelper;
import com.tchallenge.titansoft.planningpoker.prsenter.RoomFinder;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomFinderTest {
    private static final String NON_EXIST_PINCODE = "7777";
    private static final String VALID_NICKNAME = "rock";
    private static final String EXIST_PIN_CODE = "9999";

    @Test
    public void createRoomWithNonExistPincode_Should_ReturnTrue() throws Exception {
        RoomFinder roomFinder = new RoomFinder(getFakeRoomListDbHelper());
        
        boolean isSuccess = roomFinder.create(NON_EXIST_PINCODE, VALID_NICKNAME);
        
        assertTrue(isSuccess);
    }

    @Test
    public void createRoomWithExistPincode_Should_ReturnTrue() throws Exception {
        RoomFinder roomFinder = new RoomFinder(getFakeRoomListDbHelper());

        boolean isSuccess = roomFinder.create(EXIST_PIN_CODE, VALID_NICKNAME);

        assertFalse(isSuccess);
    }

    @NonNull
    private IRoomListDbHelper getFakeRoomListDbHelper() {
        return new IRoomListDbHelper() {
            @Override
            public boolean createRoom(String pinCode, String nickname) {
                if(pinCode.equals(NON_EXIST_PINCODE))
                    return true;
                else
                    return false;
            }

            @Override
            public void joinRoom(String pinCode, String nickName) {

            }

            @Override
            public void exitRoom(String pinCode, String nickName) {

            }
        };
    }


}