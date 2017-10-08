package com.tchallenge.titansoft.planningpoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FindRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_room);

        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
                finish();
            }
        });

        findViewById(R.id.btn_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
                finish();
            }
        });
    }

    public void startGame(){
        Intent intent = new Intent(this,SelectCardActivity.class);
        startActivity(intent);
    }

}
