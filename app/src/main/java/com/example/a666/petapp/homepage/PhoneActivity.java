package com.example.a666.petapp.homepage;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;

public class PhoneActivity extends BaseActivity implements View.OnClickListener {

    private ImageView image_Pull_out;
    private TextView tv_submit;
    private EditText et_name;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_phone;
    }

    @Override
    protected void initView() {
        image_Pull_out = (ImageView) findViewById(R.id.image_Pull_out);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        et_name = (EditText) findViewById(R.id.et_name);
        image_Pull_out.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }

    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.image_Pull_out:
                finish();
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }
}
