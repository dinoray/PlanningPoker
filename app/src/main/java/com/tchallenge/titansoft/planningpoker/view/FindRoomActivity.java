package com.tchallenge.titansoft.planningpoker.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tchallenge.titansoft.planningpoker.R;
import com.tchallenge.titansoft.planningpoker.contract.FindRoomContract;
import com.tchallenge.titansoft.planningpoker.prsenter.FindRoomPresenter;

import java.util.Random;

public class FindRoomActivity extends AppCompatActivity implements FindRoomContract.IFinRoomView {

    public static final String TYPE = "Type";

    private FindRoomContract.IFindRoomPresenter mFindRoomPresenter;

    private View.OnClickListener mBtnClickListener = new android.view.View.OnClickListener() {
        @Override
        public void onClick(android.view.View view) {
            String pincode = ((EditText) findViewById(R.id.edit_pincode)).getText().toString();
            String nickname = ((EditText) findViewById(R.id.edit_nickname)).getText().toString();
            if (view.getId() == R.id.btn_create)
                mFindRoomPresenter.create(pincode, nickname);
            else
                mFindRoomPresenter.join(pincode, nickname);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_room);

        mFindRoomPresenter = new FindRoomPresenter(this);

        int typeInt = getIntent().getIntExtra(TYPE, 0);
        initControllButton(typeInt);
        initPinCodeEditTxt(typeInt);
    }

    private void initControllButton(int typeInt) {
        int targetBtnResId = MainActivity.RequestCode.CREATE.equals(typeInt) ? R.id.btn_create : R.id.btn_join;
        Button button = (Button) findViewById(targetBtnResId);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(mBtnClickListener);
    }

    private void initPinCodeEditTxt(int typeInt) {
        if (MainActivity.RequestCode.CREATE.equals(typeInt)) {
            EditText pinCodeEditTxt = (EditText) findViewById(R.id.edit_pincode);
            int randomPindcode = 1000 + new Random().nextInt(9000);
            pinCodeEditTxt.setText(String.valueOf(randomPindcode));
        }
    }

    @Override
    public void joinWaitingRoom(boolean isHost) {
        Intent intent = new Intent(this, WaitingRoomActivity.class);
        String pincode = ((EditText) findViewById(R.id.edit_pincode)).getText().toString();
        String nickname = ((EditText) findViewById(R.id.edit_nickname)).getText().toString();
        intent.putExtra(WaitingRoomActivity.EXTRA_PINCODE, pincode);
        intent.putExtra(WaitingRoomActivity.EXTRA_NICKNAME, nickname);
        intent.putExtra("host", isHost);
        startActivity(intent);
        finish();
    }

    @Override
    public void showRoomExistedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.activity_find_room_error_title);
        builder.setMessage(R.string.activity_find_room_error_message);
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

}
