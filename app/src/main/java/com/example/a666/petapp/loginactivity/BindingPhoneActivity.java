package com.example.a666.petapp.loginactivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a666.petapp.HomePageActivity;
import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.entity.CJSON;
import com.example.a666.petapp.entity.UserInfo;
import com.example.a666.petapp.utils.AppUtils;
import com.example.a666.petapp.utils.Md5Encrypt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.a666.petapp.loginactivity.RegisterActivity.isMobile;

public class BindingPhoneActivity extends BaseActivity implements View.OnClickListener {

    private ImageView qq_img;
    private EditText phome_editText;
    private EditText name_editText;
    private EditText paswod_editText;
    private Button determine;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_binding_phone;
    }

    @Override
    protected void initView() {
        qq_img = (ImageView) findViewById(R.id.qq_img);
        phome_editText = (EditText) findViewById(R.id.phome_editText);
        name_editText = (EditText) findViewById(R.id.name_editText);
        paswod_editText = (EditText) findViewById(R.id.paswod_editText);
        determine = (Button) findViewById(R.id.determine);

        determine.setOnClickListener(this);
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
            case R.id.determine:
                String number_phome = phome_editText.getText().toString();
                boolean mobile = isMobile(number_phome);
                if (mobile == true) {
                    registrationMethod();
                    // Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();

                }else {
                    //注册的方法
                    Toast.makeText(this, "手机号输入不合法", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void registrationMethod() {
        String phome = phome_editText.getText().toString();
        String name = name_editText.getText().toString();
        String pasword = paswod_editText.getText().toString();
        Map<String, Object> param = new HashMap<>();
        param.put("userPhone", phome);
        param.put("userName", name);
        param.put("password", Md5Encrypt.md5(pasword, "UTF-8"));
        // 生成提交服务器的JSON字符串
        String json = CJSON.toJSONMap(param);

        FormBody.Builder builder = new FormBody.Builder();
        builder.add(CJSON.DATA, json);
        OkHttpClient ohc = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://123.56.150.230:8885/dog_family/user/register.jhtml")
                .post(builder.build())
                .build();

        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BindingPhoneActivity.this,  string, Toast.LENGTH_SHORT).show();
                        Log.e("TAG",string);

                        UserInfo userInfo = CJSON.parseObject(CJSON.getRESULT(string), UserInfo.class);
                        AppUtils.setUser(userInfo);

                        Intent intent1 = getIntent();
                        String userName = intent1.getStringExtra("userName");

                        String userIcon = intent1.getStringExtra("userIcon");

                        Intent intent = new Intent(BindingPhoneActivity.this,HomePageActivity.class);
                        intent.putExtra("userNameqq",userName);
                        intent.putExtra("userIconqq",userIcon);
                        startActivity(intent);
                        finish();

                    }
                });

            }
        });
    }

}
