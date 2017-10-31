package com.tchallenge.titansoft.planningpoker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tchallenge.titansoft.planningpoker.R;
import com.tchallenge.titansoft.planningpoker.contract.RoundResultContract;
import com.tchallenge.titansoft.planningpoker.contract.RoundResultContract.IRoundResultView;
import com.tchallenge.titansoft.planningpoker.prsenter.RoundResultPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoundResultActivity extends AppCompatActivity implements IRoundResultView {

    public static final String EXTRA_PINCODE = "pincode";
    public static final String EXTRA_NICKNAME = "nickname";
    private RoundResultAdapter mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_result);

        final String pincode = getIntent().getStringExtra(EXTRA_PINCODE);
        final String nickname = getIntent().getStringExtra(EXTRA_NICKNAME);
        final RoundResultContract.IRoundResultPresenter presenter = new RoundResultPresenter(this, pincode);
        presenter.fetchMemberInfos();

        initMemberInfoList();

        Button againBtn = (Button) findViewById(R.id.btn_again);
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoundResultActivity.this,SelectCardActivity.class);
                intent.putExtra(SelectCardActivity.EXTRA_PINCODE, pincode);
                intent.putExtra(SelectCardActivity.EXTRA_NICKNAME, nickname);
                startActivity(intent);
                finish();
            }
        });

        final Button finishBtn = (Button) findViewById(R.id.btn_finish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.removeRoom();
                finish();
            }
        });
    }

    private void initMemberInfoList() {
        ListView listView = (ListView) findViewById(R.id.memberInfoList);
        mAdatper = new RoundResultAdapter(this, R.layout.view_item_round_result, new ArrayList<String>());
        listView.setAdapter(mAdatper);
    }

    @Override
    public void onMemberInfosUpdate(Map<String, String> memberInfos) {
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, String> memberInfo : memberInfos.entrySet()) {
            result.add(memberInfo.getKey()+" : "+memberInfo.getValue());
        }
        mAdatper.clear();
        mAdatper.addAll(result);
    }
}
