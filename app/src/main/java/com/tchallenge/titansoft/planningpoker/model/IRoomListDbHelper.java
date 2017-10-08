package com.tchallenge.titansoft.planningpoker.model;

public interface IRoomListDbHelper {
    void joinRoom(String pinCode, String nickName);
    void exitRoom(final String pinCode, String nickName);
}
