package com.tchallenge.titansoft.planningpoker.contract;

public class FindRoomContract {

    public interface IFinRoomView {
        void startGame();
        void showRoomExistedDialog();
    }

    public interface IFindRoomPresenter {
        void create(String pinCode, String nickname);
        void join(String pinCode, String nickname);
    }
}
