package com.example.a666.petapp.homepage;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;

public class ShowUserInfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView image_Pull_out;
    private ImageView image_icon;
    private ImageView image_name_icon;
    private TextView tv_name;
    private RatingBar mRatingBar;
    private TextView tv_QQ_xinxi;
    private RelativeLayout mRelativeLayout_Comments;
    private TextView tv_Address;
    private TextView tv_jianjie;
    private RadioButton mRadioButton_Telephone;
    private Button mButton_fostered;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_show_user_info;
    }

    @Override
    protected void initView() {


        image_Pull_out = (ImageView) findViewById(R.id.image_Pull_out);
        image_icon = (ImageView) findViewById(R.id.image_icon);
        image_name_icon = (ImageView) findViewById(R.id.image_name_icon);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_jianjie = (TextView) findViewById(R.id.tv_jianjie);
        mRatingBar = (RatingBar) findViewById(R.id.mRatingBar);

        //寄养评论
        mRelativeLayout_Comments = (RelativeLayout) findViewById(R.id.mRelativeLayout_Comments);
        //寄养家庭地址
        tv_Address = (TextView) findViewById(R.id.tv_Address);
        //联系寄养人
        mRadioButton_Telephone = (RadioButton) findViewById(R.id.mRadioButton_Telephone);
        //预约寄养
        mButton_fostered = (Button) findViewById(R.id.mButton_fostered);

        image_Pull_out.setOnClickListener(this);
        mRelativeLayout_Comments.setOnClickListener(this);
        mButton_fostered.setOnClickListener(this);
        mRadioButton_Telephone.setOnClickListener(this);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String dizhi = intent.getStringExtra("dizhi");
        String icon = intent.getStringExtra("icon");

        int star = intent.getIntExtra("star",0);
        Glide.with(ShowUserInfoActivity.this).load(icon).into(image_name_icon);
        tv_name.setText(name);
        tv_Address.setText(dizhi);
        tv_jianjie.setText(name + "------" + dizhi);
        mRatingBar.setRating(star);


    }

    @Override
    protected void initDate() {


    }

    @Override
    protected void initLisenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_Pull_out:
                finish();
                break;

            //寄养评论
            case R.id.mRelativeLayout_Comments:

                startActivity(new Intent(ShowUserInfoActivity.this, CommentsActivity.class));
                break;
            //预约寄养跳转界面
            case R.id.mButton_fostered:
                startActivity(new Intent(ShowUserInfoActivity.this, ReservationsActivity.class));
                break;
            //联系TA
            case R.id.mRadioButton_Telephone:


                ShowPopuwindow();

                break;
        }
    }

    private void ShowPopuwindow() {
        View view = LayoutInflater.from(ShowUserInfoActivity.this).inflate(R.layout.telephone_popuwindow, null);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
//        popupWindow.setAnimationStyle(R.anim.anim_pop);
        popupWindow.setTouchInterceptor(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }

                });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        popupWindow.showAsDropDown(view, 500, 300);
        Button user_Dialing = view.findViewById(R.id.user_Dialing);
        Button send_message = view.findViewById(R.id.send_message);
        Button user_cance = view.findViewById(R.id.user_cance);

        //打电话
        user_Dialing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("057187063728");

            }
        });
        //发送消息
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFinalMessage = new Intent(Intent.ACTION_VIEW);
                intentFinalMessage.setType("vnd.android-dir/mms-sms");
                startActivity(intentFinalMessage);

            }
        });
        //取消
        user_cance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //跳过拨号界面，直接拨打电话
//        Intent intent1=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
//        startActivity(intent1);
    }
}
