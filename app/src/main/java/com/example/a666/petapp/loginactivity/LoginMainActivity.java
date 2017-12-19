package com.example.a666.petapp.loginactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.HomePageActivity;
import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.entity.CJSON;
import com.example.a666.petapp.entity.LoginBen;
import com.example.a666.petapp.entity.UserInfo;
import com.example.a666.petapp.homepage.Personal_InformationActivity;
import com.example.a666.petapp.utils.AppUtils;
import com.example.a666.petapp.utils.FileUtil;
import com.example.a666.petapp.utils.Md5Encrypt;
import com.example.a666.petapp.utils.TokenUtil;
import com.google.gson.Gson;
import com.mob.MobSDK;
import com.mob.tools.utils.UIHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.action;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener,Handler.Callback {
    private static final int MSG_ACTION_CCALLBACK = 0;
    private ImageView exit;
    private TextView register;
    private EditText phome_editText;
    private EditText password_editText;
    private TextView login_forget_password;
    private Button login;
    private ImageView weixin;
    private ImageView Q;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        initView();
        TokenUtil.init(this);
        AppUtils.setAppContext(this);
        MobSDK.init(LoginMainActivity.this, "23216e17832ec", "16da691b5af953defbfcda5517b61687");
    }





    private void initView() {
        exit = (ImageView) findViewById(R.id.exit);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        phome_editText = (EditText) findViewById(R.id.phome_editText);
        password_editText = (EditText) findViewById(R.id.password_editText);
        login_forget_password = (TextView) findViewById(R.id.login_forget_password);
        login_forget_password.setOnClickListener(this);
        login = (Button) findViewById(R.id.login);
        weixin = (ImageView) findViewById(R.id.weixin);
        Q = (ImageView) findViewById(R.id.QQ);
            Q.setOnClickListener(this);
        login.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.login:

                String phome = phome_editText.getText().toString();
                String pasword = password_editText.getText().toString();
                if (phome.equals("")|pasword.equals("")){
                    Toast.makeText(LoginMainActivity.this, "账号密码不能为空" , Toast.LENGTH_SHORT).show();

                }else {


                Map<String, Object> param = new HashMap<>();
                param.put("userPhone", phome);
                param.put("password", Md5Encrypt.md5(pasword, "UTF-8"));
                  //  FileUtil.saveToken();// 保存Token
                // 生成提交服务器的JSON字符串
                String json = CJSON.toJSONMap(param);

                FormBody.Builder builder = new FormBody.Builder();
                builder.add(CJSON.DATA, json);
                OkHttpClient ohc = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://123.56.150.230:8885/dog_family/user/login.jhtml")
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
                                Toast.makeText(LoginMainActivity.this,   string, Toast.LENGTH_SHORT).show();
                                Log.e("TAG_____denglu",string);
                                UserInfo userInfo = CJSON.parseObject(CJSON.getRESULT(string), UserInfo.class);
                                AppUtils.setUser(userInfo);
                                FileUtil.saveUser(AppUtils.userInfo);
                                Intent intent = new Intent(LoginMainActivity.this,HomePageActivity.class);

                        //登陆成功后会返回一个字符串，通过解析字符串 得到数据 将需要的数据进行传递使用；
                                Gson gson = new Gson();

                                LoginBen loginBen = gson.fromJson(string, LoginBen.class);

                                boolean ret = loginBen.isRet();


                                String userName = loginBen.getResult().getUserName();


                                long userPhone = loginBen.getResult().getUserPhone();


                                //登陆状态获取；
                                intent.putExtra("ret",ret);

                                //名字
                                intent.putExtra("userName",userName);

                                //手机号
                                intent.putExtra("userPhone",userPhone);



                                startActivity(intent);
                            }
                        });

                    }
                });
                }

                break;
            case R.id.register:
                //注册
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);

                break;
            //忘记密码
          case  R.id . login_forget_password:
              Intent intent2 = new Intent(this,Forget_The_PasswordActivity.class);
              startActivity(intent2);
              break;

            case R.id.QQ:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.SSOSetting(false);
                authorize(qq, 2);


                break;
            case R.id.weixin:


                break;


        }
    }
    //授权
    private void authorize(Platform plat, int type) {
        switch (type) {
            case 1:
                showProgressDialog(getString(R.string.opening_wechat));
                break;
            case 2:
                showProgressDialog(getString(R.string.opening_qq));
                break;
            case 3:
                showProgressDialog(getString(R.string.opening_blog));
                break;
        }
        if (plat.isAuthValid()) { //如果授权就删除授权资料
            plat.removeAccount(true);
       }
        plat.showUser(null);//授权并获取用户信息
    }



    //登陆授权成功的回调
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);   //发送消息

    }

    //登陆授权错误的回调
    @Override
    public void onError(Platform platform, int i, Throwable t) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    //登陆授权取消的回调
    @Override
    public void onCancel(Platform platform, int i) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    //登陆发送的handle消息在这里处理
    @Override
    public boolean handleMessage(Message message) {
        hideProgressDialog();
        switch (message.arg1) {
            case 1: { // 成功
                Toast.makeText(LoginMainActivity.this, "授权登陆成功", Toast.LENGTH_SHORT).show();

                //获取用户资料
                Platform platform = (Platform) message.obj;

                String userId = platform.getDb().getUserId();//获取用户账号
                String userName = platform.getDb().getUserName();//获取用户名字
                String userIcon = platform.getDb().getUserIcon();//获取用户头像
                String userGender = platform.getDb().getUserGender(); //获取用户性别，m = 男, f = 女，如果微信没有设置性别,默认返回null
                Toast.makeText(LoginMainActivity.this, "用户信息为--用户名：" + userName + "  性别：" + userGender, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginMainActivity.this,BindingPhoneActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userIcon",userIcon);
                intent.putExtra("userGender",userGender);
                intent.putExtra("userId",userId);
                startActivity(intent);

                //下面就可以利用获取的用户信息登录自己的服务器或者做自己想做的事啦!
                //。。。

            }
            break;
            case 2: { // 失败
                Toast.makeText(LoginMainActivity.this, "授权登陆失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case 3: { // 取消
                Toast.makeText(LoginMainActivity.this, "授权登陆取消", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    //显示dialog
    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    //隐藏dialog
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

}




