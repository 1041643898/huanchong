package com.example.a666.petapp.mypet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.a666.petapp.R;
import com.example.a666.petapp.mypet.mypetadapter.SubAdapter;

import java.util.ArrayList;

public class MyPetActivity extends AppCompatActivity {
    private ImageView mypet_imageview;
    private ListView mypet_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);
        initView();
        initData();
        initDate();

    }

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("捷豹");
        list.add("丽丽");
        SubAdapter subAdapter = new SubAdapter(MyPetActivity.this, list);
        mypet_listview.setAdapter(subAdapter);
        mypet_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(MyPetActivity.this, AddpetActivity.class));

            }
        });
        mypet_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        mypet_imageview = (ImageView) findViewById(R.id.mypet_imageview);
        mypet_listview = (ListView) findViewById(R.id.mypet_listview);
    }

    protected void initDate() {
        //侵入式状态栏 核心代码.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //给状态栏设置颜色。我设置透明。
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
}
