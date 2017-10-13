package com.tchallenge.titansoft.planningpoker.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tchallenge.titansoft.planningpoker.R;
import com.tchallenge.titansoft.planningpoker.contract.FindRoomContract;
import com.tchallenge.titansoft.planningpoker.prsenter.FindRoomPresenter;

import java.util.Random;

public class FindRoomActivity extends AppCompatActivity implements FindRoomContract.IFinRoomView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_room);

        final FindRoomContract.IFindRoomPresenter findRoomPresenter = new FindRoomPresenter(this);

        initControl(findRoomPresenter);
    }

    private void initControl(final FindRoomContract.IFindRoomPresenter findRoomPresenter) {
        int typeInt = getIntent().getIntExtra("Type", 0);

        if (typeInt == 1001) {
            Button createBtn = (Button) findViewById(R.id.btn_create);
            createBtn.setVisibility(View.VISIBLE);
            createBtn.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    String pincode = ((EditText) findViewById(R.id.edit_pincode)).getText().toString();
                    String nickname = ((EditText) findViewById(R.id.edit_nickname)).getText().toString();
                    findRoomPresenter.create(pincode, nickname);
                }
            });
            EditText pinCodeEditTxt = (EditText) findViewById(R.id.edit_pincode);
            Random rnd = new Random();
            int n = 1000 + rnd.nextInt(9000);
            pinCodeEditTxt.setText(String.valueOf(n));
        } else if (typeInt == 1002) {
            Button joinBtn = (Button) findViewById(R.id.btn_join);
            joinBtn.setVisibility(View.VISIBLE);
            joinBtn.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    String pincode = ((EditText) findViewById(R.id.edit_pincode)).getText().toString();
                    String nickname = ((EditText) findViewById(R.id.edit_nickname)).getText().toString();
                    findRoomPresenter.join(pincode, nickname);
                }
            });
        }
    }

    @Override
    public void startGame() {
        Intent intent = new Intent(this, SelectCardActivity.class);
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
