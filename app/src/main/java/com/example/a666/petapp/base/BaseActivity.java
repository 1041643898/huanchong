package com.example.a666.petapp.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.a666.petapp.app.MyApplication;


/**
 * Created by 666 on 2017/12/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private ScreenManager screenManager;
    private ProgressDialog progressDialog;
    private Dialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        MyApplication.baseActivity = this;
        initView();
        initDate();
        initLisenter();
        mContext = this;
        ActivityStackManager.getActivityStackManager().pushActivity(this);
        screenManager = ScreenManager.getInstance();
        //沉浸式状态栏
        screenManager.setStatusBar(isSetStatusBar, this);
        //是否旋转屏幕
        // screenManager.setScreenRoate(isAllowScreenRoate, this);
        //全屏
//       screenManager.setFullScreen(isAllowFullScreen, this);

    }

    protected abstract int getLayoutID();

    //上下文
    protected Context mContext;

    //初始化界面
    protected abstract void initView();

    //初始化数据
    protected abstract void initDate();

    //添加监听事件
    protected abstract void initLisenter();

    //是否沉浸式状态栏
    private boolean isSetStatusBar = true;
    //是否允许全屏
    private boolean isAllowFullScreen = true;
    //是否禁止旋转屏幕
    private boolean isAllowScreenRoate = false;
    private int dialogNum;

    /**
     * 是否设置沉浸状态栏
     */
    public void setStatusBar(boolean statusBar) {
        isSetStatusBar = statusBar;
    }

    /**
     * 是否设置全屏
     */
    public void setFullScreen(boolean fullScreen) {
        isAllowFullScreen = fullScreen;
    }

    /**
     * 是否设置旋转屏幕
     */
    public void setScreenRoate(boolean screenRoate) {
        isAllowScreenRoate = screenRoate;
    }


    //跳转Activity，不传参数
    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    //退出应用
    public void exitLogic() {
        ActivityStackManager.getActivityStackManager().popAllActivity();//remove all activity.
        System.exit(0);//system exit.
    }

    //显示进度条
    public void showProgressDialog(String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(MyApplication.baseActivity);
        progressDialog.setMessage(msg);
        progressDialog.show();

    }

    //创建进度条提示框
    public ProgressDialog createProgressDialog(String msg) {
        ProgressDialog progressDialog = new ProgressDialog(MyApplication.baseActivity);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    /**
     * 隐藏正在加载的进度条
     */
    public void dismissLoadDialog() {
        dialogNum--;
        if (dialogNum > 0) {
            return;
        }
        if (null != loadDialog && loadDialog.isShowing() == true) {
            loadDialog.dismiss();
            loadDialog = null;
        }
    }


    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
