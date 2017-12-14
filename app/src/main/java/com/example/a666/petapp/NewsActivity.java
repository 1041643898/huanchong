package com.example.a666.petapp;

import android.view.View;
import android.widget.ImageView;

import com.example.a666.petapp.base.BaseActivity;

public class NewsActivity extends BaseActivity {


    private ImageView imageView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.image_Pull_out);
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
