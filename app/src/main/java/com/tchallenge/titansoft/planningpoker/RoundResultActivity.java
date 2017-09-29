package com.tchallenge.titansoft.planningpoker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RoundResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_result);

        String result = getIntent().getStringExtra("SELECT_RESULT");

        TextView textView = (TextView) findViewById(R.id.txt_result);
        textView.setText(result);

        Button againBtn = (Button) findViewById(R.id.btn_again);
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoundResultActivity.this,SelectCardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final Button finishBtn = (Button) findViewById(R.id.btn_finish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoundResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
