package com.tchallenge.titansoft.planningpoker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tchallenge.titansoft.planningpoker.R;

public class MainActivity extends AppCompatActivity {
    public enum RequestCode {
        CREATE(1001),
        JOIN(1002);

        private final int value;

        RequestCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public boolean equals(int value){
            return this.value == value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.btn_create);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFindRoomActivity(RequestCode.CREATE);
            }
        });

        Button exitBtn = (Button) findViewById(R.id.btn_join);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFindRoomActivity(RequestCode.JOIN);
            }
        });
    }

    public void startFindRoomActivity(RequestCode reqCode) {
        Intent intent = new Intent(this, FindRoomActivity.class);
        intent.putExtra("Type", reqCode.getValue());
        startActivity(intent);
    }
}
