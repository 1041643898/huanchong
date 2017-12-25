package com.example.a666.petapp.loginactivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;

public class Forget_The_PasswordActivity extends BaseActivity implements View.OnClickListener {
    private ImageView get_back;
    private TextView submit;
    private RelativeLayout relative;
    private EditText forget_phome_editText;
    private EditText register_phome;
    private TextView register_auth_code;
    private EditText forget_password_editText;
    private EditText forget_affirm_password_editText;
    Handler handler=new Handler();
    private  int T=60;
    private LinearLayout mLine;
    private Button mBtnQueDing;
    private PopupWindow popupWindow;

    //忘记密码页面
    @Override
    protected int getLayoutID() {
        return R.layout.activity_forget__the__password;
    }

    protected void initView() {
        get_back = (ImageView) findViewById(R.id.get_back);
        submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        forget_phome_editText = (EditText) findViewById(R.id.forget_phome_editText);
        register_phome = (EditText) findViewById(R.id.register_phome);
        register_auth_code = (TextView) findViewById(R.id.register_auth_code);
        register_auth_code.setOnClickListener(this);
        forget_password_editText = (EditText) findViewById(R.id.forget_password_editText);
        forget_affirm_password_editText = (EditText) findViewById(R.id.forget_affirm_password_editText);
        mLine = (LinearLayout) findViewById(R.id.mLine);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_auth_code:
                String number_phome = register_phome.getText().toString();
                boolean mobile = isMobile(number_phome);
                if (mobile == true) {
                    new Thread(new MyCountDownTimer()).start();//开始执行
                } else {
                    Toast.makeText(this, "手机号输入不合法", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.submit:
                //提交按钮
                showPopWindow();

                break;
            case R.id.get_back:

                finish();
                break;


        }
    }



    //提交的提示框
    private void showPopWindow() {
        View inflate = LayoutInflater.from(Forget_The_PasswordActivity.this).inflate(R.layout.pop, null);
        mBtnQueDing = inflate.findViewById(R.id.btn_queding);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(mLine, Gravity.CENTER,0,-200);

        if(popupWindow.isShowing()){
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 0.5f;
            getWindow().setAttributes(params);
        }

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
        mBtnQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

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






//手机号验证码倒计时
    class MyCountDownTimer implements Runnable{

        @Override
        public void run() {

            //倒计时开始，循环
            while (T > 0) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        register_auth_code.setClickable(false);
                        register_auth_code.setText(T + "秒后重新开始");
                    }
                });
                try {
                    Thread.sleep(1000); //强制线程休眠1秒，就是设置倒计时的间隔时间为1秒。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                T--;
            }

            //倒计时结束，也就是循环结束
            handler.post(new Runnable() {
                @Override
                public void run() {
                    register_auth_code.setClickable(true);
                    register_auth_code.setText("倒计时开始");
                }
            });
            T = 60; //最后再恢复倒计时时长
        }
    }


}
