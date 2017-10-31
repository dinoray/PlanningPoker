package com.tchallenge.titansoft.planningpoker.prsenter;

import android.support.annotation.NonNull;

import com.tchallenge.titansoft.planningpoker.contract.FindRoomContract;
import com.tchallenge.titansoft.planningpoker.model.IRoomListDbHelper;
import com.tchallenge.titansoft.planningpoker.model.RoomListDbHelper;

public class FindRoomPresenter implements FindRoomContract.IFindRoomPresenter, RoomListDbHelper.IRoomDbListener {

    private final FindRoomContract.IFinRoomView mFindRoomView;

    private RoomFinder mRoomFinder;

    public FindRoomPresenter(FindRoomContract.IFinRoomView findRoomView) {
        mFindRoomView = findRoomView;

        mRoomFinder = new RoomFinder(getRoomListDbHelper());
    }

    @NonNull
    protected IRoomListDbHelper getRoomListDbHelper() {
        return new RoomListDbHelper(this);
    }

    @Override
    public void create(String pinCode, String nickname) {
        if (isNickNameInvalid(nickname)) return;

        boolean isSuccess = mRoomFinder.create(pinCode, nickname);
        if(!isSuccess) {
            mFindRoomView.showRoomExistedDialog();
        }
    }

    @Override
    public void join(String pinCode, String nickname) {
        if (isNickNameInvalid(nickname)) return;

        mRoomFinder.join(pinCode, nickname);
    }

    private boolean isNickNameInvalid(String nickname) {
        if(nickname.isEmpty()) {
            mFindRoomView.showNoNameDialog();
            return true;
        }
        return false;
    }

    @Override
    public void onRoomJoin(String pinCode, boolean isSuccess, boolean isHost) {
        mFindRoomView.joinWaitingRoom(isHost);
    }

    @Override
    public void onRoomExit(String pinCode, boolean isSuccess) {

    }
}
