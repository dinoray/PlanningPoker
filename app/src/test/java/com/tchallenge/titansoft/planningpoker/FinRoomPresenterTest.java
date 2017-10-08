package com.tchallenge.titansoft.planningpoker;

import android.support.annotation.NonNull;

import com.tchallenge.titansoft.planningpoker.contract.FindRoomContract;
import com.tchallenge.titansoft.planningpoker.model.IRoomListDbHelper;
import com.tchallenge.titansoft.planningpoker.prsenter.FindRoomPresenter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FinRoomPresenterTest {
    private static final String VALID_NICKNAME = "rock";
    private static final String EXIST_PIN_CODE = "9999";

    @Test
    public void createRoomWithExistPincode_Should_ShowRoomExistedDialog() {
        FindRoomContract.IFinRoomView finRoomView = mock(FindRoomContract.IFinRoomView.class);
        FindRoomPresenter findRoomPresenter = new FindRoomPresenter(finRoomView) {
            @NonNull
            @Override
            protected IRoomListDbHelper getRoomListDbHelper() {
                return getFakeRoomListDbHelper();
            }
        };
        
        findRoomPresenter.create(EXIST_PIN_CODE, VALID_NICKNAME);
        
        verify(finRoomView, times(1)).showRoomExistedDialog();
    }

    @NonNull
    private IRoomListDbHelper getFakeRoomListDbHelper() {
        return new IRoomListDbHelper() {
            @Override
            public void joinRoom(String pinCode, String nickName) {

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