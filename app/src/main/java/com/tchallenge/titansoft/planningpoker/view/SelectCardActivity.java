package com.tchallenge.titansoft.planningpoker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tchallenge.titansoft.planningpoker.R;

public class SelectCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);
    }

    public void BtnClick(View view){
        Intent intent = new Intent(getBaseContext(), RoundResultActivity.class);
        intent.putExtra("SELECT_RESULT", view.getTag().toString());
        startActivity(intent);
        finish();
    }
}
