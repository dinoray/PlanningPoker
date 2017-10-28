package com.tchallenge.titansoft.planningpoker.prsenter;

import com.tchallenge.titansoft.planningpoker.contract.WaitingRoomContract;
import com.tchallenge.titansoft.planningpoker.model.RoomDbHelper;

import java.util.List;

public class WaitingRoomPresenter implements WaitingRoomContract.IWaitingRoomPresenter, RoomDbHelper.IRoomDbListener {

    private final WaitingRoomContract.IWaitingRoomView mView;
    private final String mPinCode;
    private RoomDbHelper mRoomDbHelper;

    public WaitingRoomPresenter(WaitingRoomContract.IWaitingRoomView view, String pinCode) {
        mPinCode = pinCode;
        mView = view;
        mRoomDbHelper = new RoomDbHelper(this);
    }

    @Override
    public void initMemberList() {
        mRoomDbHelper.initRoom(mPinCode);
    }

    @Override
    public void startRound() {
        mRoomDbHelper.notifyStart(mPinCode);

    }

    @Override
    public void onMembesUpdate(List<String> nicknames) {
        mView.resetMembers(nicknames);
    }

    @Override
    public void onStartChange() {
        mView.startSelectCardActivity();
    }
}
