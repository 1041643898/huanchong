package com.example.ta;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.ta.adapter.PetTypeAdapter;
import com.example.ta.fragments.FragmentCat;
import com.example.ta.fragments.FragmentDog;
import com.example.ta.fragments.FragmentSmall;

import java.util.ArrayList;

public class PetTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout pettype_tablayout;
    private ViewPager pettype_viewpager;
    private ImageView pettype_finsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);
        initView();
        initData();
        initDate();

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

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("狗");
        list.add("猫");
        list.add("小宠");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentDog());
        fragments.add(new FragmentCat());
        fragments.add(new FragmentSmall());
        PetTypeAdapter petTypeAdapter = new PetTypeAdapter(getSupportFragmentManager(), list, fragments);
        pettype_tablayout.setupWithViewPager(pettype_viewpager);
        pettype_viewpager.setAdapter(petTypeAdapter);

    }

    private void initView() {
        pettype_tablayout = (TabLayout) findViewById(R.id.pettype_tablayout);
        pettype_viewpager = (ViewPager) findViewById(R.id.pettype_viewpager);
        pettype_finsh = (ImageView) findViewById(R.id.pettype_finsh);
        pettype_finsh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pettype_finsh:
               finish();
                break;
        }
    }

}
