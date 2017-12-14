package com.example.a666.petapp;

import android.view.View;
import android.widget.ImageView;

import com.example.a666.petapp.base.BaseActivity;

public class InstructionsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_instructions;
    }

    @Override
    protected void initView() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
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
                case  R.id.iv_back:
                finish();
                break;
            }
    }
}
