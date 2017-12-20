package com.example.a666.petapp.homepage;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;

public class ReservationsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView image_Pull_out;
    private TextView begin_time;
    private TextView foster_time;
    private TextView end_time;
    private ImageView pet_photo;
    private TextView pet_name;
    private TextView pet_desc;
    private ImageView pet_sex;
    private ImageView delete_pet;
    private TextView pet_service_name;
    private ImageView pet_service_num_dec;
    private ImageView pet_service_num_add;
    private TextView pet_service_money;
    private TextView pet_service_name2;
    private ImageView pet_service_num_dec2;
    private ImageView pet_service_num_add2;
    private TextView pet_service_money2;
    private TextView pet_service_num;
    private TextView pet_service_num2;

    private TextView tv_TotalMoney;
    private LinearLayout foster_pet_list;
    private TextView show_item_name;
    private RelativeLayout pet_foster_add;
    private EditText user_news;
    private TextView foster_order_all_money;
    private Button show_jiyang;

    int a = 0;
    int b = 0;
    int c = 60;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_reservations;
    }

    @Override
    protected void initView() {
        image_Pull_out = (ImageView) findViewById(R.id.image_Pull_out);
        //起始时间
        begin_time = (TextView) findViewById(R.id.begin_time);
        // 总共天数
        foster_time = (TextView) findViewById(R.id.foster_time);
        //结束时间
        end_time = (TextView) findViewById(R.id.end_time);
        //宠物照片
        pet_photo = (ImageView) findViewById(R.id.pet_photo);
        //宠物名字
        pet_name = (TextView) findViewById(R.id.pet_name);
        //
        pet_desc = (TextView) findViewById(R.id.pet_desc);
        //
        pet_sex = (ImageView) findViewById(R.id.pet_sex);
        //
        delete_pet = (ImageView) findViewById(R.id.delete_pet);
        //
        pet_service_name = (TextView) findViewById(R.id.pet_service_name);
        //
        pet_service_num_dec = (ImageView) findViewById(R.id.pet_service_num_dec);
        //
        pet_service_num_add = (ImageView) findViewById(R.id.pet_service_num_add);
        //
        pet_service_money = (TextView) findViewById(R.id.pet_service_money);
        pet_service_num = (TextView) findViewById(R.id.pet_service_num);
        pet_service_num2 = (TextView) findViewById(R.id.pet_service_num2);
        //
        pet_service_name2 = (TextView) findViewById(R.id.pet_service_name2);
        //
        pet_service_num_dec2 = (ImageView) findViewById(R.id.pet_service_num_dec2);
        //
        pet_service_num_add2 = (ImageView) findViewById(R.id.pet_service_num_add2);
        //
        pet_service_money2 = (TextView) findViewById(R.id.pet_service_money2);
        //总共金额
        tv_TotalMoney = (TextView) findViewById(R.id.tv_TotalMoney);
        //
        foster_pet_list = (LinearLayout) findViewById(R.id.foster_pet_list);
        //
        show_item_name = (TextView) findViewById(R.id.show_item_name);
        //
        pet_foster_add = (RelativeLayout) findViewById(R.id.pet_foster_add);
        //
        user_news = (EditText) findViewById(R.id.user_news);
        //
        foster_order_all_money = (TextView) findViewById(R.id.foster_order_all_money);
        //
        show_jiyang = (Button) findViewById(R.id.show_jiyang);

        show_jiyang.setOnClickListener(this);
        pet_service_num_dec.setOnClickListener(this);
        pet_service_num_dec2.setOnClickListener(this);

        pet_service_num_add.setOnClickListener(this);
        pet_service_num_add2.setOnClickListener(this);
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

            case R.id.pet_service_num_dec:
                a--;
                if (a <= 3) {
                    a = 1;
                    pet_service_num.setText(a + "");

                    return;
                }
                break;
            case R.id.pet_service_num_add:
                String string = pet_service_num.getText().toString();
                a++;
                if (a <= 3) {

                    pet_service_num.setText(a + "");

                } else {
                    if (a == 4) {
                       
                        Toast.makeText(ReservationsActivity.this, "最多可添加三个", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.pet_service_num_dec2:
                b--;
                if (b <= 3) {
                    b = 1;
                    pet_service_num2.setText(b + "");

                    return;
                } else if (b == 0) {

                    Toast.makeText(ReservationsActivity.this, "到底啦~", Toast.LENGTH_SHORT).show();
                    break;
                }
                break;
            case R.id.pet_service_num_add2:
                String string2 = pet_service_num2.getText().toString();
                b++;
                if (b <= 3) {
                    pet_service_num2.setText(b + "");
                } else if (b == 4) {
                    Toast.makeText(ReservationsActivity.this, "最多可添加三个", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.show_jiyang:
                startActivity(new Intent(ReservationsActivity.this, Order_SubmissionActivity.class));
                break;
        }
    }

    private void submit() {
        // validate
        String news = user_news.getText().toString().trim();
        if (TextUtils.isEmpty(news)) {
            Toast.makeText(this, "和他说点什么吧", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
