package com.tchallenge.titansoft.planningpoker.contract;

import java.util.List;

public class WaitingRoomContract {


    public interface IWaitingRoomView {
        void resetMembers(List<String> nicknames);
        void startSelectCardActivity();
    }

    public interface IWaitingRoomPresenter {
        void initMemberList();
        void exitRoom(String nickname, boolean isHost);
        void startRound();
        void uninit();
    }
}
