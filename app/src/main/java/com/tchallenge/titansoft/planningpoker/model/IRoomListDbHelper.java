package com.tchallenge.titansoft.planningpoker.model;

public interface IRoomListDbHelper {
    boolean createRoom(String pinCode, String nickname);
    void joinRoom(String pinCode, String nickName);
    void exitRoom(final String pinCode, String nickName);
}
