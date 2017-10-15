package com.tchallenge.titansoft.planningpoker;

import android.support.annotation.NonNull;

import com.tchallenge.titansoft.planningpoker.model.IRoomListDbHelper;
import com.tchallenge.titansoft.planningpoker.prsenter.RoomFinder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomFinderTest {
    private static final String NON_EXIST_PINCODE = "7777";
    private static final String VALID_NICKNAME = "rock";
    private static final String EXIST_PIN_CODE = "9999";

    @Test
    public void createRoomWithNonExistPincode_Should_ReturnTrue() {
        RoomFinder roomFinder = new RoomFinder(getFakeRoomListDbHelper());
        
        boolean isSuccess = roomFinder.create(NON_EXIST_PINCODE, VALID_NICKNAME);
        
        assertTrue(isSuccess);
    }

    @Test
    public void createRoomWithExistPincode_Should_ReturnTrue() {
        RoomFinder roomFinder = new RoomFinder(getFakeRoomListDbHelper());

        boolean isSuccess = roomFinder.create(EXIST_PIN_CODE, VALID_NICKNAME);

        assertFalse(isSuccess);
    }

    @NonNull
    private IRoomListDbHelper getFakeRoomListDbHelper() {
        return new IRoomListDbHelper() {
            @Override
            public void joinRoom(String pinCode, String nickName, boolean isHost) {

            }

            @Override
            public void exitRoom(String pinCode, String nickName) {

            }

            @Override
            public List<String> getExistedRooms() {
                List<String> existedRooms = new ArrayList<>();
                existedRooms.add(EXIST_PIN_CODE);
                return existedRooms;
            }
        };
    }


}