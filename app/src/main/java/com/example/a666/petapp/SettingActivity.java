package com.example.a666.petapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.homepage.Setting.MainNewfeatures;
import com.example.a666.petapp.homepage.Setting.ScoreActivity;
import com.example.a666.petapp.homepage.Setting.SuggestActivity;

public class SettingActivity extends BaseActivity {

    private ImageView img_tuichu;
    private ImageView imageView1;
    private RelativeLayout rl_productproposal;
    private RelativeLayout recy_pingfen;
    private ImageView imageView2;
    private RelativeLayout rl_new;
    private ImageView imageView3;
    private ImageView imageView4;
    private RelativeLayout rl_updateguan;
    private ImageView imageView11;
    private CheckBox cb_wifi;
    private ImageView imageView12;
    private ImageView imageView5;
    private TextView tv_huan;
    private ImageView imageView13;
    private RelativeLayout rl_clear;
    private Button btn_exit;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        img_tuichu = (ImageView) findViewById(R.id.img_tuichu);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        rl_productproposal = (RelativeLayout) findViewById(R.id.rl_productproposal);
        recy_pingfen = (RelativeLayout) findViewById(R.id.recy_pingfen);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        rl_new = (RelativeLayout) findViewById(R.id.rl_new);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        rl_updateguan = (RelativeLayout) findViewById(R.id.rl_updateguan);
        imageView11 = (ImageView) findViewById(R.id.imageView11);
        cb_wifi = (CheckBox) findViewById(R.id.cb_wifi);
        imageView12 = (ImageView) findViewById(R.id.imageView12);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        tv_huan = (TextView) findViewById(R.id.tv_huan);
        imageView13 = (ImageView) findViewById(R.id.imageView13);
        rl_clear = (RelativeLayout) findViewById(R.id.rl_clear);
        btn_exit = (Button) findViewById(R.id.btn_exit);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {
        //产品建议
        rl_productproposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this, SuggestActivity.class);
                startActivity(intent);
            }
        });
        //新功能介绍
        rl_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this, MainNewfeatures.class);
                startActivity(intent);
            }
        });
        //去评分
        recy_pingfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });
        img_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
