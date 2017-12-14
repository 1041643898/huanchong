package com.example.a666.petapp.order;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.a666.petapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloseActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String PHONE_PATTERN = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    private Button btn_call_person;
    private EditText user_name;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_close);
        initView();
    }

    private void initView() {
        btn_call_person = (Button) findViewById(R.id.btn_call_person);

        btn_call_person.setOnClickListener(this);
        user_name = (EditText) findViewById(R.id.user_phone);
        user_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call_person:

                ShowPopupWindow_Gender(v);

                break;
        }
    }

    // PopupWindow  性别选择
    private void ShowPopupWindow_Gender(View view) {
               view = LayoutInflater.from(CloseActivity.this).inflate(R.layout.pop_call, null);
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
                isMatchered(PHONE_PATTERN,user_name.getText().toString().trim());

                popupWindow.dismiss();
            }


        });
        but_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                popupWindow.dismiss();
            }
        });


    }

    public boolean isMatchered(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + user_name.getText().toString().trim());
            intent.setData(data);
            startActivity(intent);

            return true;
        } else {

            Toast.makeText(this, "您输入的不是手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
