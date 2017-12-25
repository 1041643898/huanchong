package com.example.a666.petapp.loginactivity;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.entity.CJSON;
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

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView register_cancel;
    private TextView login;
    private EditText register_phome;
    private TextView register_auth_code;
    private EditText auth_code_editText;
    private EditText user_name_editText;
    private EditText register_pasword;
    private Button register;
    private ImageView register_weixin;
    private ImageView register_QQ;






    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;

    }

    //初始化窗口属性，让状态栏和导航栏透明
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
    }
    protected void initView() {
        initWindow();
        register_cancel = (TextView) findViewById(R.id.register_cancel);
        register_cancel.setOnClickListener(this);


        register_phome = (EditText) findViewById(R.id.register_phome);
        register_auth_code = (TextView) findViewById(R.id.register_auth_code);



        user_name_editText = (EditText) findViewById(R.id.user_name_editText);
        register_pasword = (EditText) findViewById(R.id.register_pasword);
        register = (Button) findViewById(R.id.register);
        register_weixin = (ImageView) findViewById(R.id.register_weixin);
        register_QQ = (ImageView) findViewById(R.id.register_QQ);
        register.setOnClickListener(this);
        AppUtils.setAppContext(this);


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
            case R.id.register:
                String phome = register_phome.getText().toString();
                String name = user_name_editText.getText().toString();
                String pasword = register_pasword.getText().toString();
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
                                Toast.makeText(RegisterActivity.this, "注册成功" + string, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });


                break;
            case R.id.register_cancel:
                finish();
                break;


        }
    }
    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }





}
