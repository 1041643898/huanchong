package com.example.a666.petapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a666.petapp.base.BaseActivity;

//钱包
public class WalletActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView text1;
    private TextView text2;
    private ImageView iv2;
    private TextView tv2;
    private ImageView iv3;
    private TextView tv3;
    private ImageView iv4;
    private TextView tv4;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initView() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        text1 = (TextView) findViewById(R.id.text1);
        text1.setOnClickListener(this);
        text2 = (TextView) findViewById(R.id.text2);
        text2.setOnClickListener(this);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv2.setOnClickListener(this);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setOnClickListener(this);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv3.setOnClickListener(this);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setOnClickListener(this);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv4.setOnClickListener(this);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setOnClickListener(this);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_back:
                    finish();
                    break;
            }
    }
}
