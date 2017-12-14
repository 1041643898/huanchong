package com.example.a666.petapp.homepage;

import android.view.View;
import android.widget.ImageView;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;

public class OrderActivity extends BaseActivity {


    private ImageView imageView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
    }
}
