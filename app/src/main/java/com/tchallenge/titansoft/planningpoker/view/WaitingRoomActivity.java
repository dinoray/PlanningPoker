package com.tchallenge.titansoft.planningpoker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.tchallenge.titansoft.planningpoker.R;
import com.tchallenge.titansoft.planningpoker.contract.WaitingRoomContract;
import com.tchallenge.titansoft.planningpoker.prsenter.WaitingRoomPresenter;

import java.util.List;


public class WaitingRoomActivity extends AppCompatActivity implements WaitingRoomContract.IWaitingRoomView{

    private static final String TAG = WaitingRoomActivity.class.getSimpleName();

    private WaitingRoomPresenter mWaitingRoomPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        boolean isHost = getIntent().getBooleanExtra("host",false);

        final String pincode = getIntent().getStringExtra("pincode");
        TextView tvPincode = (TextView) findViewById(R.id.tv_pincode);
        tvPincode.setText(pincode);

        if (isHost){
            findViewById(R.id.btn_start).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWaitingRoomPresenter.startRound();
                    startSelectCardActivity();
                }
            });
        }

        mWaitingRoomPresenter = new WaitingRoomPresenter(this, pincode);
        mWaitingRoomPresenter.initMemberList();
    }

    @Override
    public void startSelectCardActivity() {
        Intent intent = new Intent(WaitingRoomActivity.this, SelectCardActivity.class);
        startActivity(intent);
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
