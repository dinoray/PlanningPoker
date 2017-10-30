package com.tchallenge.titansoft.planningpoker.prsenter;

import com.tchallenge.titansoft.planningpoker.contract.WaitingRoomContract;
import com.tchallenge.titansoft.planningpoker.model.RoomDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WaitingRoomPresenter implements WaitingRoomContract.IWaitingRoomPresenter, RoomDbHelper.IRoomDbListener {

    private final WaitingRoomContract.IWaitingRoomView mView;
    private final String mPinCode;
    private RoomDbHelper mRoomDbHelper;

    public WaitingRoomPresenter(WaitingRoomContract.IWaitingRoomView view, String pinCode) {
        mPinCode = pinCode;
        mView = view;
        mRoomDbHelper = new RoomDbHelper(mPinCode,this);
    }

    @Override
    public void initMemberList() {
        mRoomDbHelper.initRoom();
    }

    @Override
    public void startRound() {
        mRoomDbHelper.notifyStart();

    }

    @Override
    public void onMembesUpdate(Map<String, String> memberInfos) {
        List<String> nicknames = new ArrayList<>();
        for(Map.Entry<String, String> memberInfo : memberInfos.entrySet()) {
            nicknames.add(memberInfo.getKey());
        }
        mView.resetMembers(nicknames);
    }

    @Override
    public void onStartChange() {
        mView.startSelectCardActivity();
    }
}
