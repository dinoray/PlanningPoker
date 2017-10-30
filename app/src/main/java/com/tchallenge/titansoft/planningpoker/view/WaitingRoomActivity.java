package com.tchallenge.titansoft.planningpoker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tchallenge.titansoft.planningpoker.R;
import com.tchallenge.titansoft.planningpoker.contract.WaitingRoomContract;
import com.tchallenge.titansoft.planningpoker.prsenter.WaitingRoomPresenter;

import java.util.List;


public class WaitingRoomActivity extends AppCompatActivity implements WaitingRoomContract.IWaitingRoomView{

    private static final String TAG = WaitingRoomActivity.class.getSimpleName();
    public static final String EXTRA_PINCODE = "pincode";
    public static final String EXTRA_NICKNAME = "nickname";

    private WaitingRoomContract.IWaitingRoomPresenter mWaitingRoomPresenter;
    private String mPincode;
    private String mNickName;
    private boolean mIsHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        mIsHost = getIntent().getBooleanExtra("host",false);
        mPincode = getIntent().getStringExtra(EXTRA_PINCODE);
        mNickName = getIntent().getStringExtra(EXTRA_NICKNAME);

        TextView tvPincode = (TextView) findViewById(R.id.tv_pincode);
        tvPincode.setText(mPincode);

        if (mIsHost){
            findViewById(R.id.btn_start).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWaitingRoomPresenter.startRound();
                }
            });
        }

        mWaitingRoomPresenter = new WaitingRoomPresenter(this, mPincode);
        mWaitingRoomPresenter.initMemberList();
    }

    @Override
    public void startSelectCardActivity() {
        Intent intent = new Intent(this, SelectCardActivity.class);
        intent.putExtra(SelectCardActivity.EXTRA_PINCODE, mPincode);
        intent.putExtra(SelectCardActivity.EXTRA_NICKNAME, mNickName);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        mWaitingRoomPresenter.exitRoom(mNickName, mIsHost);
        super.onBackPressed();
    }

    @Override
    public void resetMembers(List<String> nicknames) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_wait);
        layout.removeAllViews();
        for (String nickname : nicknames) {
            TextView tv = new TextView(this);
            tv.setText(nickname);
            layout.addView(tv);
        }
    }
}
