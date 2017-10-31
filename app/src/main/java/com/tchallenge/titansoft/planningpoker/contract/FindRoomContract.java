package com.tchallenge.titansoft.planningpoker.contract;

public class FindRoomContract {

    public interface IFinRoomView {
        void joinWaitingRoom(boolean isHost);
        void showRoomExistedDialog();
        void showNoNameDialog();
    }

    public interface IFindRoomPresenter {
        void create(String pinCode, String nickname);
        void join(String pinCode, String nickname);
    }
}
