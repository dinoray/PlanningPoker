package com.tchallenge.titansoft.planningpoker.prsenter;

import com.tchallenge.titansoft.planningpoker.contract.RoundResultContract;
import com.tchallenge.titansoft.planningpoker.model.RoomDbHelper;

import java.util.Map;

public class RoundResultPresenter implements RoundResultContract.IRoundResultPresenter, RoomDbHelper.IRoomDbListener {

    private final RoundResultContract.IRoundResultView mView;
    private RoomDbHelper mRoomDbHelper;

    public RoundResultPresenter(RoundResultContract.IRoundResultView view, String pincode) {
        mView = view;
        mRoomDbHelper = new RoomDbHelper(pincode);
        mRoomDbHelper.setRoomDbListener(this);
    }


    @Override
    public void onMembesUpdate(Map<String, String> memberInfos) {
        mView.onMemberInfosUpdate(memberInfos);
    }

    @Override
    public void onStartChange() {

    }

    @Override
    public void fetchMemberInfos() {
        mRoomDbHelper.initRoom();
    }

    @Override
    public void removeRoom() {
        mRoomDbHelper.removeRoom();
    }
}
