package com.example.a666.petapp;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.adapter.PopupAdapter;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.homepage.OrderActivity;
import com.example.a666.petapp.homepage.Personal_InformationActivity;
import com.example.a666.petapp.homepage.PopupButton;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class HomePageActivity extends BaseActivity implements View.OnClickListener {

    private ImageView image_Personage_Centre;
    private EditText edit_sousuo;
    private ImageView image_Orientation;
    private LinearLayout linear_Nearby;
    private LinearLayout linear_Pat_Genre;
    private LinearLayout linear_ShaiXuan;

    //点击两次退出程序
    private static boolean isExit = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    private PopupButton popu_but_nearby;
    private PopupButton popu_but_pat_genre;
    private PopupButton popu_but_Pat_Genre;
    private PopupButton popu_but_ShaiXuan;

    private ListView list_lv;

    private ImageView image_name_icon;
    private TextView tv_name;
    private TextView tv_phone;

    private LinearLayout linear_name;
    private LinearLayout linear_Xiaoxi;
    private LinearLayout linear_Pet;
    private LinearLayout linear_DingDan;
    private LinearLayout linear_QianBao;
    private LinearLayout linear_XunZhi;
    private LinearLayout linear_SheZhi;
    private Button but_ShenQing;

    private DrawerLayout drawerLayout;
    private LinearLayout liner_nav;
    private PullToRefreshListView main_listview;
    private TextView tv_pet;
    private DrawerLayout activity_na;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    //初始化窗口属性，让状态栏和导航栏透明
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_homepage;
    }

    @Override
    protected void initView() {
        initWindow();

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        liner_nav = (LinearLayout) findViewById(R.id.liner_nav);
        linear_name = (LinearLayout) findViewById(R.id.linear_name);
        image_name_icon = (ImageView) findViewById(R.id.image_name_icon);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);

        linear_Xiaoxi = (LinearLayout) findViewById(R.id.linear_Xiaoxi);
        linear_Pet = (LinearLayout) findViewById(R.id.linear_Pet);

        linear_DingDan = (LinearLayout) findViewById(R.id.linear_DingDan);
        linear_QianBao = (LinearLayout) findViewById(R.id.linear_QianBao);

        linear_XunZhi = (LinearLayout) findViewById(R.id.linear_XunZhi);
        linear_SheZhi = (LinearLayout) findViewById(R.id.linear_SheZhi);
        but_ShenQing = (Button) findViewById(R.id.but_ShenQing);

        //点击侧换个人中心
//-------------上侧滑界面-----------------下主页面-----------------------------
        //搜索城市
        edit_sousuo = (EditText) findViewById(R.id.edit_sousuo);
        //点击显示侧滑界面
        image_Personage_Centre = (ImageView) findViewById(R.id.image_Personage_Centre);
        //定位
        image_Orientation = (ImageView) findViewById(R.id.image_Orientation);
        //附近优先
        linear_Nearby = (LinearLayout) findViewById(R.id.linear_Nearby);
        //宠物类型
        linear_Pat_Genre = (LinearLayout) findViewById(R.id.linear_Pat_Genre);
        //筛选
        linear_ShaiXuan = (LinearLayout) findViewById(R.id.linear_ShaiXuan);

        //刷新ListView
        main_listview = (PullToRefreshListView) findViewById(R.id.main_listview);


        image_Personage_Centre.setOnClickListener(this);
        edit_sousuo.setOnClickListener(this);
        image_Orientation.setOnClickListener(this);
        linear_Nearby.setOnClickListener(this);
        linear_Pat_Genre.setOnClickListener(this);
        linear_ShaiXuan.setOnClickListener(this);
        linear_name.setOnClickListener(this);
        linear_Pet.setOnClickListener(this);
        but_ShenQing.setOnClickListener(this);
        linear_DingDan.setOnClickListener(this);
        linear_Xiaoxi.setOnClickListener(this);
        linear_XunZhi.setOnClickListener(this);
        linear_QianBao.setOnClickListener(this);
        linear_SheZhi.setOnClickListener(this);
//----------------------------------------------------------------------------------
        popu_but_nearby = (PopupButton) findViewById(R.id.popu_but_nearby);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.popup, null);
        ListView lv = (ListView) view.findViewById(R.id.lv);
        final String[] arr = {"附近优先", "好评优先", "订单优先", "价格从高到低", "价格从低到高"};
        final PopupAdapter adapter = new PopupAdapter(this, R.layout.popup_item, arr, R.drawable.normal, R.drawable.press);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setPressPostion(position);
                adapter.notifyDataSetChanged();
                popu_but_nearby.setText(arr[position]);
                popu_but_nearby.hidePopup();
            }
        });
        popu_but_nearby.setPopupView(view);
//----------------------------------------------------------------------------------
        popu_but_pat_genre = (PopupButton) findViewById(R.id.popu_but_Pat_Genre);
        LayoutInflater inflater1 = LayoutInflater.from(this);
        View view1 = inflater1.inflate(R.layout.popup, null);
        ListView lv1 = (ListView) view1.findViewById(R.id.lv);
        final String[] arr1 = {"小型犬", "中型犬", "大型犬", "猫", "小宠", "幼犬"};
        final PopupAdapter adapter1 = new PopupAdapter(this, R.layout.popup_item, arr1, R.drawable.normal, R.drawable.press);
        lv1.setAdapter(adapter1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter1.setPressPostion(position);
                adapter1.notifyDataSetChanged();
                popu_but_pat_genre.setText(arr1[position]);
                popu_but_pat_genre.hidePopup();
            }
        });
        popu_but_pat_genre.setPopupView(view1);
        popu_but_ShaiXuan = (PopupButton) findViewById(R.id.popu_but_ShaiXuan);


    }

    //----------------------------------------------------------------------------------


    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.image_Personage_Centre:
                if (drawerLayout.isDrawerOpen(liner_nav)) {
                    drawerLayout.closeDrawer(liner_nav);
                } else {
                    drawerLayout.openDrawer(liner_nav);
                }

                break;
            case R.id.edit_sousuo:
                //搜索城市

                break;

            case R.id.image_Orientation:
                //定位

                break;

            //侧滑的点击事件
            case R.id.linear_name:
                startActivity(new Intent(HomePageActivity.this, Personal_InformationActivity.class));
                break;

            //消息嗯嗯我去
            case R.id.linear_Xiaoxi:
                startActivity(new Intent(HomePageActivity.this, NewsActivity.class));
                break;
            //宠物
            case R.id.linear_Pet:

                break;
            //订单
            case R.id.linear_DingDan:
                Intent intent1=new Intent(HomePageActivity.this, OrderActivity.class);
                startActivity(intent1);
                break;
            //钱包
            case R.id.linear_QianBao:
                Intent intent4=new Intent(HomePageActivity.this, WalletActivity.class);
                startActivity(intent4);
                break;
            //须知
            case R.id.linear_XunZhi:
                Intent intent3=new Intent(HomePageActivity.this, InstructionsActivity.class);
                startActivity(intent3);
                break;
            //
            case R.id.linear_SheZhi:
                Intent intent5=new Intent(HomePageActivity.this, SettingActivity.class);
                startActivity(intent5);
                break;
            //申请成为寄养家庭
            case R.id.but_ShenQing:
                Intent intent2=new Intent(HomePageActivity.this, FosterfamiliesActivity.class);
                startActivity(intent2);
                break;


        }

    }
}
