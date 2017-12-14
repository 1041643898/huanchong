package com.example.a666.petapp.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.homepage.date.CustomDatePicker;
import com.example.a666.petapp.homepage.round_imageview.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Personal_InformationActivity extends BaseActivity implements View.OnClickListener {
    private RoundImageView image_icon;
    private LinearLayout linear_Name;
    private LinearLayout liner_Gender;
    private TextView tv_Gender;
    private LinearLayout linear_Date_of_Birth;
    private LinearLayout linear_Phone;
    private LinearLayout linear_WeiXin;
    private TextView tv_QQ_xinxi;
    private LinearLayout linear_QQ;
    private LinearLayout linear_Address;
    private ImageView image_Pull_out;
    private PopupWindow popupWindow;
    private TextView tv_date;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    public final String USER_IMAGE_NAME = "image.png";
    public final String USER_CROP_IMAGE_NAME = "temporary.png";
    public Uri imageUriFromCamera;
    public Uri cropImageUri;
    public final int GET_IMAGE_BY_CAMERA_U = 5001;
    public final int CROP_IMAGE_U = 5003;
    protected static final int CHOOSE_PICTURE = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_personal__information;
    }

    @Override
    protected void initView() {
        //退出界面
        image_Pull_out = (ImageView) findViewById(R.id.image_Pull_out);
        //上传头像
        image_icon = (RoundImageView) findViewById(R.id.image_icon);

        //设置名字
        linear_Name = (LinearLayout) findViewById(R.id.linear_Name);
        //设置性别
        liner_Gender = (LinearLayout) findViewById(R.id.liner_Gender);
        tv_Gender = (TextView) findViewById(R.id.tv_Gender);
        //设置出生日期
        linear_Date_of_Birth = (LinearLayout) findViewById(R.id.linear_Date_of_Birth);
        //显示出生日期
        tv_date = (TextView) findViewById(R.id.tv_Date);
        //设置手机号
        linear_Phone = (LinearLayout) findViewById(R.id.linear_Phone);
        //设置微信号
        linear_WeiXin = (LinearLayout) findViewById(R.id.linear_WeiXin);
        //QQ 信息未完善系列
        tv_QQ_xinxi = (TextView) findViewById(R.id.tv_QQ_xinxi);
        //QQ
        linear_QQ = (LinearLayout) findViewById(R.id.linear_QQ);
        //联系地址 跳转
        linear_Address = (LinearLayout) findViewById(R.id.linear_Address);
        image_Pull_out.setOnClickListener(this);
        image_icon.setOnClickListener(this);
        linear_Name.setOnClickListener(this);
        liner_Gender.setOnClickListener(this);
        linear_Date_of_Birth.setOnClickListener(this);
        linear_Phone.setOnClickListener(this);
        linear_WeiXin.setOnClickListener(this);
        linear_QQ.setOnClickListener(this);
        linear_Address.setOnClickListener(this);

        //设置出生日期
        ShowDate_of_Birth();
    }

    @Override
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

    @Override
    protected void initLisenter() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //退出
            case R.id.image_Pull_out:
                finish();
                break;
            //头像上传
            case R.id.image_icon:
                ShowPopupWindow_Icon(view);
                break;
            //修改名字
            case R.id.linear_Name:

                startActivity(new Intent(this, Modif_NameActivity.class));
                break;
            //性别
            case R.id.liner_Gender:
                ShowPopupWindow_Gender(view);
                break;
            //出生日期
            case R.id.linear_Date_of_Birth:
                customDatePicker1.show(tv_date.getText().toString());

                break;
            //手机
            case R.id.linear_Phone:
                startActivity(new Intent(this, PhoneActivity.class));
                break;
            //微信
            case R.id.linear_WeiXin:
                startActivity(new Intent(this, WeiXinActivity.class));
                break;
            //  QQ
            case R.id.linear_QQ:
                startActivity(new Intent(this, QQActivity.class));
                break;
            //联系地址
            case R.id.linear_Address:
                startActivity(new Intent(this, Contact_AddressActivity.class));
                break;
        }
    }
    //出生日期

    private void ShowDate_of_Birth() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tv_date.setText(now.split(" ")[0]);
        tv_date.setText(now);

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_date.setText(time.split(" ")[0]);

            }
        }, "1900-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动

        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_date.setText(time);
            }
        }, "1900-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动
    }

    // PopupWindow  性别选择
    private void ShowPopupWindow_Gender(View view) {
        view = LayoutInflater.from(Personal_InformationActivity.this).inflate(R.layout.popupwindow_gender, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setTouchInterceptor(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }

                });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        popupWindow.showAsDropDown(view, 150, 300);
        Button but_nan = view.findViewById(R.id.but_nan);
        Button but_nv = view.findViewById(R.id.but_nv);

        but_nan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Gender.setText("男");
                popupWindow.dismiss();
            }


        });
        but_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Gender.setText("女");
                popupWindow.dismiss();
            }
        });


    }

    // PopupWindow   头像上传
    private void ShowPopupWindow_Icon(View view) {
        view = LayoutInflater.from(Personal_InformationActivity.this).inflate(R.layout.popupwindow_icon, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//        popupWindow.setAnimationStyle(R.anim.anim_pop);
        popupWindow.setTouchInterceptor(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }

                });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        popupWindow.showAsDropDown(view, 150, 300);
        Button but_pai = view.findViewById(R.id.but_pai);
        Button but_Phone = view.findViewById(R.id.but_Phone);

        Button but_out = view.findViewById(R.id.but_out);
        //拍照
        but_pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                popupWindow.dismiss();
            }
        });
        but_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.dismiss();
            }


        });

        but_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

    }


}
