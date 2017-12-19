package com.example.a666.petapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.example.a666.petapp.utils.LocaUtil;

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
        final LocaUtil locaUtil = new LocaUtil(this);
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

        //清除缓存
        img_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rl_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(SettingActivity.this);
                normalDialog.setTitle("提示");
                normalDialog.setMessage("确定清除缓存?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                locaUtil.clearAppCache();
                                String cacheSize = locaUtil.getCacheSize();
                                tv_huan.setText(cacheSize);
                            }
                        });
                normalDialog.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                            }
                        });
                // 显示
                normalDialog.show();
            }

        });

        String cacheSize = locaUtil.getCacheSize();
        tv_huan.setText(cacheSize);



    }


}
