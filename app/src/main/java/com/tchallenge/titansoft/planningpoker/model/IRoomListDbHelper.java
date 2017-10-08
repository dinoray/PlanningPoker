package com.tchallenge.titansoft.planningpoker.model;

import java.util.List;

public interface IRoomListDbHelper {
    void joinRoom(String pinCode, String nickName);
    void exitRoom(final String pinCode, String nickName);
    List<String> getExistedRooms();
}
