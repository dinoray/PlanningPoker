package com.tchallenge.titansoft.planningpoker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tchallenge.titansoft.planningpoker.R;
import com.tchallenge.titansoft.planningpoker.model.RoomDbHelper;
import com.tchallenge.titansoft.planningpoker.model.RoomMemberDbHelper;

public class SelectCardActivity extends AppCompatActivity {

    public static final String EXTRA_PINCODE = "pincode";
    public static final String EXTRA_NICKNAME = "nickname";

    private RoomMemberDbHelper mRoomMemberDbHelper;
    private String mPinCode;
    private String mNickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);

        mPinCode = getIntent().getStringExtra(EXTRA_PINCODE);
        mNickName = getIntent().getStringExtra(EXTRA_NICKNAME);
        mRoomMemberDbHelper = new RoomMemberDbHelper(mPinCode, mNickName);
    }

    @Override
    public void onBackPressed() {

    }

    public void BtnClick(View view){
        String result = view.getTag().toString();
        mRoomMemberDbHelper.updateResult(result);
        Intent intent = new Intent(getBaseContext(), RoundResultActivity.class);
        intent.putExtra(RoundResultActivity.EXTRA_PINCODE, mPinCode);
        intent.putExtra(RoundResultActivity.EXTRA_NICKNAME, mNickName);
        startActivity(intent);
        finish();
    }
}
