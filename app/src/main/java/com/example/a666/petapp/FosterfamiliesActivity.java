package com.example.a666.petapp;

import android.view.View;
import android.widget.ImageView;

import com.example.a666.petapp.base.BaseActivity;

public class FosterfamiliesActivity extends BaseActivity {


    private ImageView imageView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_fosterfamilies;
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.img_tuichu);
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
