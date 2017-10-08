package com.tchallenge.titansoft.planningpoker.prsenter;

import com.tchallenge.titansoft.planningpoker.contract.FindRoomContract;
import com.tchallenge.titansoft.planningpoker.model.RoomListDbHelper;

public class FindRoomPresenter implements FindRoomContract.IFindRoomPresenter, RoomListDbHelper.IRoomDbListener {

    private final FindRoomContract.IFinRoomView mFindRoomView;

    private RoomFinder mRoomFinder;

    public FindRoomPresenter(FindRoomContract.IFinRoomView findRoomView) {
        mFindRoomView = findRoomView;

        mRoomFinder = new RoomFinder(new RoomListDbHelper(this));
    }

    @Override
    public void create(String pinCode, String nickname) {
        boolean isSuccess = mRoomFinder.create(pinCode, nickname);
        if(!isSuccess) {
            mFindRoomView.showRoomExistedDialog();
        }
    }

    @Override
    public void join(String pinCode, String nickname) {
        mRoomFinder.join(pinCode, nickname);
    }

    @Override
    public void onRoomJoin(String pinCode, boolean isSuccess) {
        mFindRoomView.startGame();
    }

    @Override
    public void onRoomExit(String pinCode, boolean isSuccess) {

    }
}
