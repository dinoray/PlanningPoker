package com.tchallenge.titansoft.planningpoker;

import android.support.test.runner.AndroidJUnit4;

import com.tchallenge.titansoft.planningpoker.model.RoomListDbHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RoomListDbHelperTest {

    private Object mLock = new Object();

    @Test
    public void joinRoom() {
        RoomListDbHelper roomListDbHelper = new RoomListDbHelper(new RoomListDbHelper.IRoomDbListener() {
            @Override
            public void onRoomJoin(String pinCode, boolean isSuccess) {
                assertTrue(pinCode.equals("8888"));
                assertTrue(isSuccess);
                RoomListDbHelperTest.this.notifyRoomCreate();
            }

            @Override
            public void onRoomExit(String pinCode, boolean isSuccess) {

            }
        });

        roomListDbHelper.joinRoom("8888", "gg");
        waitRoomCreate();
    }

    @Test
    public void existRoom() {
        RoomListDbHelper roomListDbHelper = new RoomListDbHelper(new RoomListDbHelper.IRoomDbListener() {
            @Override
            public void onRoomJoin(String pinCode, boolean isSuccess) {
                assertTrue(pinCode.equals("8888"));
                assertTrue(isSuccess);
                RoomListDbHelperTest.this.notifyRoomCreate();
            }

            @Override
            public void onRoomExit(String pinCode, boolean isSuccess) {

            }
        });

        roomListDbHelper.joinRoom("8888", "gg");
        roomListDbHelper.exitRoom("8888", "gg");
        waitRoomCreate();
    }

    private void notifyRoomCreate() {
        synchronized (mLock) {
            mLock.notify();
        }
    }

    private void waitRoomCreate() {
        synchronized (mLock) {
            try {
                mLock.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
